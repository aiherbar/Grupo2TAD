/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo2tad;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class MainView {

    final Calendar cal;
    final TopAndRightMenu top;
    final HorizontalLayout main;
   final HorizontalLayout interviews;
    
    public MainView() {
        this.cal = new Calendar();
        this.top = new TopAndRightMenu();
        this.main = new HorizontalLayout();
        this.interviews = new HorizontalLayout();
        fillInterviews();
        java.util.Calendar c = java.util.Calendar.getInstance();
        cal.setStartDate(c.getTime());
        c.add(java.util.Calendar.DAY_OF_MONTH, 30);
        cal.setEndDate(c.getTime());
        cal.setWidth(100, Sizeable.Unit.PERCENTAGE);
        
        main.addComponents(new VerticalLayout(top.getTopBar(), cal,interviews),top.getRightBar());
    }
    
    
    public HorizontalLayout getView(){
    return main;
    }
    
 
    public void fillInterviews(){
    
        for (int i = 0; i < 8; i++) {
            Label label = new Label("Entrevista: "+i);
            label.setStyleName("card");
            interviews.addComponent(label);
        }
    
    }

}
