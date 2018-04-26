/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo2tad;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Anais
 */
public class TopAndLeftMenu {

    private final HorizontalLayout topBar;
    private final Button createInterView;
    private final Button createInterviwer;
    private final Button createIntervied;

    final VerticalLayout leftBar;
    final FormLayout form;

    final TextField name;
    final TextField apellidos;
    final TextField DNI;
    final Button submitInteviewer;
    final Button submitIntervied;

    public TopAndLeftMenu() {
        topBar = new HorizontalLayout();
        createInterView = new Button("Crear entrevistas");
        createInterviwer = new Button("Crear entrevistador");
        createIntervied = new Button("Crear entrevistado");
        leftBar = new VerticalLayout();
        form = new FormLayout();
        name = new TextField("Nombre");
        apellidos = new TextField("Apellidos");
        DNI = new TextField("DNI");
        submitInteviewer = new Button("Aceptar");
        submitIntervied = new Button("Aceptar");
        
        
        form.addComponents(name,apellidos,DNI,submitInteviewer,submitIntervied);
        topBar.addComponents(createInterView, createInterviwer, createIntervied);
        leftBar.addComponent(form);
        leftBar.setVisible(false);

    }

    public HorizontalLayout getTopBar() {
        return topBar;
    }

    public void createEvents() {

        createInterView.addClickListener(e -> {
            if (leftBar.isVisible()) {
                leftBar.setVisible(false);
            } else {

                leftBar.setVisible(true);

            }

        });
    }

}
