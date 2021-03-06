/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo2tad;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.List;
import proyecto_tad.entity.Entrevista;
import proyecto_tad.entity.Entrevistado;
import proyecto_tad.entity.Entrevistador;

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
    final FormLayout formEntrevistados;
    final FormLayout formEntrevistadores;
    final FormLayout formEntrevista;

    //Datos de el formulario de Personas
    final TextField name;
    final TextField DNI;
    final TextField nameEntrevistador;
    final TextField DNIEntrevistador;
    final TextField Departamento;
    final Button submitInteviewer;
    final Button submitIntervied;

    //Datos de el formulario de la entrevista
    final ComboBox entrevistadores;
    final ComboBox entrevistados;
    final TextField Lugar;
    final Button submitInterview;

    private DBController controller;

    public TopAndRightMenu() {
        controller = DBController.getInstance();
        topBar = new HorizontalLayout();
        createInterView = new Button("Crear entrevistas");
        createInterviwer = new Button("Crear entrevistador");
        createIntervied = new Button("Crear entrevistado");
        
        this.createInterView.addStyleName(ValoTheme.BUTTON_PRIMARY);
        this.createInterviwer.addStyleName(ValoTheme.BUTTON_PRIMARY);
        this.createIntervied.addStyleName(ValoTheme.BUTTON_PRIMARY);
        //Añadir estilos
        createInterView.addStyleName("component-margin");
        createInterviwer.addStyleName("component-margin");
        createIntervied.addStyleName("component-margin");
        
        leftBar = new VerticalLayout();
        leftBar.setHeight("700px");
        this.leftBar.addStyleName("component-padding");
        this.leftBar.addStyleName("left-border");
        leftBar.setWidth("400px");
        formEntrevistados = new FormLayout();
        formEntrevistadores = new FormLayout();
        formEntrevista = new FormLayout();

        //Crear objectos del primer formulario
        name = new TextField("Nombre");
        DNI = new TextField("DNI");
        nameEntrevistador = new TextField("Nombre");
        DNIEntrevistador = new TextField("DNI");
        Departamento = new TextField("Departamento");
        submitInteviewer = new Button("Aceptar");
        submitIntervied = new Button("Aceptar");

        //Crear objectos del segundo formulario
        entrevistadores = new ComboBox("Entrevistador");
        entrevistados = new ComboBox("Entrevistado");
        entrevistadores.addItems(controller.getEntrevistadores());
        entrevistados.addItems(controller.getEntrevistados());
        Lugar = new TextField("Lugar");
        submitInterview = new Button("Aceptar");

        formEntrevistados.addComponents(name, DNI, submitIntervied);
        formEntrevistadores.addComponents(nameEntrevistador, DNIEntrevistador, Departamento, submitInteviewer);
        formEntrevista.addComponents(entrevistadores, entrevistados, Lugar, submitInterview);

        topBar.addComponents(createInterView, createInterviwer, createIntervied);

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
            leftBar.addComponent(formEntrevistadores);

        });

        createIntervied.addClickListener(e -> {

            leftBar.setVisible(true);
            leftBar.removeAllComponents();
            leftBar.addComponent(formEntrevistados);

        });

        createInterView.addClickListener(e -> {
            leftBar.setVisible(true);
            leftBar.removeAllComponents();
            leftBar.addComponent(formEntrevista);

        });

        submitInteviewer.addClickListener(e -> {
            if (!DNIEntrevistador.getValue().isEmpty() && !nameEntrevistador.getValue().isEmpty() && !Departamento.getValue().isEmpty()) {
                controller.setEntrevistador(DNIEntrevistador.getValue(), nameEntrevistador.getValue(), Departamento.getValue());
                DNIEntrevistador.setValue("");
                nameEntrevistador.setValue("");
                Departamento.setValue("");
                entrevistadores.removeAllItems();
                entrevistadores.addItems(controller.getEntrevistadores());
                Notification.show("Entrevistador creado con exito",
                        Notification.Type.HUMANIZED_MESSAGE);
            }

        });

        submitIntervied.addClickListener(e -> {
            if (!DNI.getValue().isEmpty() && !name.getValue().isEmpty()) {
                controller.setEntrevistado(DNI.getValue(), name.getValue());
                DNI.setValue("");
                name.setValue("");
                entrevistados.removeAllItems();
                entrevistados.addItems(controller.getEntrevistados());
                Notification.show("Entrevistado creado con exito",
                        Notification.Type.HUMANIZED_MESSAGE);
            }
        });

        submitInterview.addClickListener(e -> {

            if (entrevistadores.getValue() != null && entrevistados.getValue() != null && !Lugar.getValue().isEmpty()) {
                Entrevistador entrevistador = (Entrevistador) entrevistadores.getValue();
                Entrevistado entrevistado = (Entrevistado) entrevistados.getValue();
                controller.setEntrevista(entrevistado.getId(), entrevistador.getId(), "", Lugar.getValue());
                entrevistadores.select(null);
                entrevistados.select(null);
                Lugar.setValue("");
                Notification.show("Entrevista creada con exito",
                        Notification.Type.HUMANIZED_MESSAGE);
                resetInterviewTable();
            }
        });
    }

    private void resetInterviewTable() {
        List<Entrevista> entrevistas = controller.getEntrevistasSinFecha();
        MainView.interviews.removeAllItems();
        for (Entrevista entrevista : entrevistas) {
            MainView.interviews.addItem(new Object[]{controller.getEntrevistado(entrevista.getIdEntrevistado()), controller.getEntrevistador(entrevista.getIdEntrevistador()), entrevista.getLugar()}, entrevista.getId());
        }
                MainView.interviews.setPageLength(MainView.interviews.size());

    }

}
