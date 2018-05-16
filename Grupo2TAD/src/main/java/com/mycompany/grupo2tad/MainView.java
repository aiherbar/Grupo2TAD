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
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.calendar.CalendarComponentEvents;
import com.vaadin.ui.components.calendar.CalendarTargetDetails;
import com.vaadin.ui.components.calendar.event.BasicEvent;
import com.vaadin.ui.components.calendar.event.BasicEventProvider;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import proyecto_tad.entity.Entrevista;
import proyecto_tad.entity.Entrevistado;
import proyecto_tad.entity.Entrevistador;

public class MainView {

    final Calendar cal;
    final TopAndRightMenu top;
    final HorizontalLayout main;
    final static Table interviews = new Table();
    

    private DBController controller;

    public MainView() {
        controller = DBController.getInstance();
        this.top = new TopAndRightMenu();
        this.main = new HorizontalLayout();
        this.main.setSizeFull();
        this.main.addStyleName("component-padding");
        this.interviews.addContainerProperty("Entrevistado", Entrevistado.class, null);
        this.interviews.addContainerProperty("Entrevistador", Entrevistador.class, null);
        this.interviews.addContainerProperty("Lugar", String.class, null);

        List<Entrevista> entrevistas = controller.getEntrevistasSinFecha();
        for (Entrevista entrevista : entrevistas) {
            this.interviews.addItem(new Object[]{controller.getEntrevistado(entrevista.getIdEntrevistado()), controller.getEntrevistador(entrevista.getIdEntrevistador()), entrevista.getLugar()}, entrevista.getId());
        }

        this.interviews.setDragMode(Table.TableDragMode.ROW);
        this.interviews.setPageLength(this.interviews.size());
        this.interviews.setWidth("1000px");
        this.interviews.setHeight("140px");

        this.cal = new Calendar();
        entrevistas = controller.getEntrevistasConFecha();
        for (Entrevista entrevista : entrevistas) {
            BasicEvent newEvent = new BasicEvent();
            String s = String.format("%04d", entrevista.getId());
            newEvent.setCaption("Con " + controller.getEntrevistado(entrevista.getIdEntrevistado()) + " (" + String.format("%04d", entrevista.getId()) + ")");
            newEvent.setDescription("Entrevistador: " + controller.getEntrevistador(entrevista.getIdEntrevistador()) + " "
                    + "Lugar: " + entrevista.getLugar());
            newEvent.setStart(entrevista.getFecha());
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(entrevista.getFecha());
            calendar.add(java.util.Calendar.MINUTE, 60);
            newEvent.setEnd(calendar.getTime());
            cal.addEvent(newEvent);
        }
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
                    int id = Integer.parseInt(calevent.getCalendarEvent().getCaption().substring(calevent.getCalendarEvent().getCaption().length() - 5, calevent.getCalendarEvent().getCaption().length() - 1));

                    controller.updateEntrevistaFecha(id, calevent.getNewStart());
                }
            }
        });

        cal.setWidth("1000px");
        
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

        String eventType = "Con " + draggedItem.getItemProperty("Entrevistado").getValue().toString() + " (" + String.format("%04d", transferable.getItemId()) + ")";

        String eventDescription = "Entrevistador: " + (String) draggedItem.getItemProperty("Entrevistador").getValue().toString() + " "
                + "Lugar: " + (String) draggedItem.getItemProperty("Lugar").getValue();

        Collection c = draggedItem.getItemPropertyIds();
        BasicEvent newEvent = new BasicEvent();
        newEvent.setAllDay(!details.hasDropTime());
        newEvent.setCaption(eventType);
        newEvent.setDescription(eventDescription);
        newEvent.setStart(dropTime);
        newEvent.setEnd(endTime);

        controller.updateEntrevistaFecha((int) transferable.getItemId(), dropTime);

        Entrevista e = controller.getEntrevista((int) transferable.getItemId());
        BasicEventProvider ep = (BasicEventProvider) details.getTargetCalendar().getEventProvider();
        ep.addEvent(newEvent);
    }

}
