/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo2tad;

import com.vaadin.server.FontAwesome;
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
    private final MainView mainView;
    private final ManagementView managementView;
    private final ChartsView chartView;
    
    final HorizontalLayout mainLayout;
    public MainMenu() {
        mainView = new MainView();
        managementView = new ManagementView();
        chartView = new ChartsView();
        menu = new CssLayout();
        mainLayout = new HorizontalLayout();
        mainLayout.addComponent(mainView.getView());
        
        menu.addStyleName(ValoTheme.MENU_PART_LARGE_ICONS);
        Label l = new Label("Interviews Dashboard");
        l.setStyleName(ValoTheme.LABEL_H3);
        menu.addComponent(l);

        Button main = new Button("Principal");
        main.setIcon(FontAwesome.CALENDAR_O);
        main.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        main.addClickListener( e -> {
            mainLayout.removeAllComponents();
            mainLayout.addComponent(mainView.getView());
        });  
        menu.addComponent(main);

        Button charts = new Button("Estadisticas");
        DragAndDropWrapper draggable = new DragAndDropWrapper(charts);
        draggable.setDragStartMode(DragAndDropWrapper.DragStartMode.WRAPPER);
        charts.setIcon(FontAwesome.BAR_CHART_O);
        charts.setPrimaryStyleName(ValoTheme.MENU_ITEM);
           charts.addClickListener( e -> {
            mainLayout.removeAllComponents();
            mainLayout.addComponent(chartView.getMain());
        });  
        menu.addComponent(draggable);

        Button management = new Button("Gestión");
        management.setIcon(FontAwesome.TABLE);
        management.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        management.addClickListener( e -> {
            mainLayout.removeAllComponents();
            mainLayout.addComponent(managementView.getMain());
        });  
        
        menu.addComponent(management);
        
        Button logOut = new Button("Log Out");
        logOut.setIcon(FontAwesome.ARROW_LEFT);
        logOut.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        logOut.addClickListener( e -> {
            //Log out aqui
        });  
        
        menu.addComponent(logOut);
        

    }

    public CssLayout getMenu() {
        return menu;
    }

    public HorizontalLayout getCurrent(){
     return mainLayout;
    }
}
