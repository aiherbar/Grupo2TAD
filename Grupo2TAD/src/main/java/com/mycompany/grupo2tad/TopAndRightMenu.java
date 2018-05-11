/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo2tad;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author Anais
 */
public class TopAndRightMenu {

    private final HorizontalLayout topBar;
    private final Button createInterView;
    private final Button createInterviwer;
    private final Button createIntervied;

    final VerticalLayout leftBar;
    final FormLayout formPersonas;
    final FormLayout formEntrevista;
    
    
    //Datos de el formulario de Personas
    final TextField name;
    final TextField apellidos;
    final TextField DNI;
    final Button submitInteviewer;
    final Button submitIntervied;
    
    //Datos de el formulario de la entrevista
    final ComboBox entrevistadores;
    final ComboBox entrevistados;
    final Button submitInterview;

    private DBController controller;
    
    public TopAndRightMenu() {
        controller = new DBController();
        topBar = new HorizontalLayout();
        createInterView = new Button("Crear entrevistas");
        createInterviwer = new Button("Crear entrevistador");
        createIntervied = new Button("Crear entrevistado");
        leftBar = new VerticalLayout();
        formPersonas = new FormLayout();
        formEntrevista = new FormLayout();

        
        //Crear objectos del primer formulario
        name = new TextField("Nombre");
        apellidos = new TextField("Apellidos");
        DNI = new TextField("DNI");
        submitInteviewer = new Button("Aceptar");
        submitIntervied = new Button("Aceptar");
        
        
        //Crear objectos del segundo formulario
        entrevistadores = new ComboBox("Entrevistador");
        entrevistados = new ComboBox("Entrevistado");
        submitInterview = new Button("Aceptar");
        
        formPersonas.addComponents(name,apellidos,DNI,submitInteviewer,submitIntervied);
        formEntrevista.addComponents(entrevistadores,entrevistados,submitInterview);
        
        topBar.addComponents(createInterView, createInterviwer, createIntervied);
        leftBar.addComponent(formPersonas);
        leftBar.setVisible(false);

        this.createEvents();
    }

    public HorizontalLayout getTopBar() {
        return topBar;
    }

    public VerticalLayout getRightBar() {
        return leftBar;
    }

    private void createEvents() {

        createInterviwer.addClickListener(e -> {

            
                leftBar.setVisible(true);
                leftBar.removeAllComponents();
                leftBar.addComponent(formPersonas);
                submitIntervied.setVisible(false);
                submitInteviewer.setVisible(true);
            

        });
        
       createIntervied.addClickListener(e -> {

                leftBar.setVisible(true);
                leftBar.removeAllComponents();
                leftBar.addComponent(formPersonas);
                submitIntervied.setVisible(true);
                submitInteviewer.setVisible(false);
            

        });
        
        
        createInterView.addClickListener(e -> {
                leftBar.setVisible(true);
                leftBar.removeAllComponents();
                leftBar.addComponent(formEntrevista);
            

        });
    }

}
