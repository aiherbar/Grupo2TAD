/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo2tad;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

/**
 *
 * @author Anais
 */
public class TopMenu {
    
    private final HorizontalLayout topBar;

    public TopMenu() {
        topBar = new HorizontalLayout();
        Button createInterView = new Button("Crear entrevistas");
        Button createInterviwer = new Button("Crear entrevistador");
        Button createIntervied = new Button("Crear entrevistado");
        topBar.addComponents(createInterView,createInterviwer,createIntervied);
        
        
        
    }

    public HorizontalLayout getTopBar() {
        return topBar;
    }
    
    
    
}
