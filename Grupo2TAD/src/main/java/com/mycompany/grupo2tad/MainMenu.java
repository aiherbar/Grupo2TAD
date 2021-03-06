/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo2tad;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author Anais
 */
public class MainMenu {

    private final CssLayout menu;
    private MainView mainView;
    private ManagementView managementView;
    private ChartsView chartView;
    final HorizontalLayout mainLayout;
    private DBController controller;
    private WrappedSession session;

    public MainMenu() {
        controller = DBController.getInstance();
        mainView = new MainView();
        menu = new CssLayout();
        mainLayout = new HorizontalLayout();
        mainLayout.addComponent(mainView.getView());

        menu.addStyleName(ValoTheme.MENU_PART_LARGE_ICONS);
        Label l = new Label("Interviews Dashboard");
        l.setStyleName(ValoTheme.LABEL_H3);
        l.addStyleName("text-menu");
        menu.addComponent(l);

        Button main = new Button("Principal");
        main.setIcon(FontAwesome.CALENDAR_O);
        main.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        main.addStyleName("text-menu");
        main.addClickListener(e -> {
            mainLayout.removeAllComponents();
            mainView = new MainView();
            mainLayout.addComponent(mainView.getView());
        });
        menu.addComponent(main);

        Button charts = new Button("Estadisticas");
        DragAndDropWrapper draggable = new DragAndDropWrapper(charts);
        draggable.setDragStartMode(DragAndDropWrapper.DragStartMode.WRAPPER);
        charts.setIcon(FontAwesome.BAR_CHART_O);
        charts.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        charts.addStyleName("text-menu");
        charts.addClickListener(e -> {
            mainLayout.removeAllComponents();
            chartView = new ChartsView();
            mainLayout.addComponent(chartView.getMain());
        });
        menu.addComponent(draggable);

        Button management = new Button("Gestión");
        management.setIcon(FontAwesome.TABLE);
        management.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        management.addStyleName("text-menu");
        management.addClickListener(e -> {
            mainLayout.removeAllComponents();
            managementView = new ManagementView();
            mainLayout.addComponent(managementView.getMain());
        });

        menu.addComponent(management);

        Button logOut = new Button("Log Out");
        logOut.setIcon(FontAwesome.ARROW_LEFT);
        logOut.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        logOut.addStyleName("text-menu");
        logOut.addClickListener(e -> {
            controller.closeInstance();
            session.invalidate();
        });

        menu.addComponent(logOut);

    }

    public CssLayout getMenu() {
        return menu;
    }

    public HorizontalLayout getCurrent() {
        return mainLayout;
    }

    public void setSession(WrappedSession session) {

        this.session = session;
    }

}
