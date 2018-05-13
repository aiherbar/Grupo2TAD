/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo2tad;

import com.vaadin.data.Item;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.calendar.CalendarComponentEvents;
import com.vaadin.ui.components.calendar.CalendarTargetDetails;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.components.calendar.event.BasicEventProvider;
import java.util.Date;
import java.util.List;
import proyecto_tad.entity.Entrevista;

public class MainView {

    final Calendar cal;
    final TopAndRightMenu top;
    final HorizontalLayout main;
    final Table interviews;
    
    private DBController controller;

    public MainView() {
        controller = DBController.getInstance();
        this.top = new TopAndRightMenu();
        this.main = new HorizontalLayout();
        this.interviews = new Table("Entrevistas");

        this.interviews.addContainerProperty("Entrevistado", Object.class, null);
        this.interviews.addContainerProperty("Entrevistador", Object.class, null);
        this.interviews.addContainerProperty("Lugar", String.class, null);

        List<Entrevista> entrevistas = controller.getEntrevistasNoAgendadas();
        for (Entrevista entrevista : entrevistas) {
            
        }
        this.interviews.addItem(new Object[]{"Pepe", "Ramiro", "Madrid"}, 1);
        this.interviews.addItem(new Object[]{"Luis", "Ramiro", "Madrid"}, 2);
        this.interviews.addItem(new Object[]{"Raquel", "Juan", "Jaen"}, 3);

        this.interviews.setDragMode(Table.TableDragMode.ROW);
        this.interviews.setPageLength(this.interviews.size());

        this.cal = new Calendar();
        cal.setDropHandler(new DropHandler() {
            public AcceptCriterion getAcceptCriterion() {
                return AcceptAll.get();
            }

            public void drop(DragAndDropEvent event) {
                CalendarTargetDetails details = (CalendarTargetDetails) event.getTargetDetails();

                Table.TableTransferable transferable = (Table.TableTransferable) event.getTransferable();
                createEvent(details, transferable);
                interviews.removeItem(transferable.getItemId());
                interviews.setPageLength(interviews.size());
            }
        });

        cal.addListener(new Calendar.Listener() {
            @Override
            public void componentEvent(Component.Event event) {
                CalendarComponentEvents.MoveEvent calevent = (CalendarComponentEvents.MoveEvent) event;
                if (calevent.getNewStart() != null) {
                    Notification.show("nueva fecha: " + calevent.getNewStart().toString());
                }
            }
        });

        main.addComponents(new VerticalLayout(this.top.getTopBar(), this.cal, this.interviews), this.top.getRightBar());
    }

    public HorizontalLayout getView() {
        return main;
    }

    protected void createEvent(CalendarTargetDetails details, Table.TableTransferable transferable) {
        Date dropTime = details.getDropTime();
        java.util.Calendar timeCalendar = details.getTargetCalendar().getInternalCalendar();
        timeCalendar.setTime(dropTime);
        timeCalendar.add(java.util.Calendar.MINUTE, 60);
        Date endTime = timeCalendar.getTime();

        Item draggedItem = transferable.getSourceComponent().getItem(transferable.getItemId());

        String eventType = "Entrevista con " + (String) draggedItem.getItemProperty("Entrevistado").getValue();

        String eventDescription = "Entrevistador: " + (String) draggedItem.getItemProperty("Entrevistador").getValue() + " "
                + "Lugar: " + (String) draggedItem.getItemProperty("Lugar").getValue();

        BasicEvent newEvent = new BasicEvent();
        newEvent.setAllDay(!details.hasDropTime());
        newEvent.setCaption(eventType);
        newEvent.setDescription(eventDescription);
        newEvent.setStart(dropTime);
        newEvent.setEnd(endTime);

        BasicEventProvider ep = (BasicEventProvider) details.getTargetCalendar().getEventProvider();
        ep.addEvent(newEvent);
    }
}
