/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo2tad;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;

/**
 *
 * @author Anais
 */
public class ManagementView {

    final HorizontalLayout main;

    //Tablas
    final Table tableInterviews;
    final Table tableInterviewers;
    final Table tableIntervieweds;

    //Botones superiores
    final Button showInterviews;
    final Button showInterviewers;
    final Button showIntervieweds;

    //Elementos de la derecha
    final VerticalLayout rightBar;

    final FormLayout formEntrevistados;
    final FormLayout formEntrevistadores;
    final FormLayout formEntrevista;

    //Datos de el formulario de Personas
    final TextField name;
    final TextField apellidos;
    final TextField DNI;
    final TextField nameEntrevistador;
    final TextField apellidosEntrevistador;
    final TextField DNIEntrevistador;
    final TextField Departamento;
    final Button updateInteviewer;
    final Button updateIntervied;
    final Button removeInteviewer;
    final Button removeIntervied;

    //Datos de el formulario de la entrevista
    final ComboBox entrevistadores;
    final ComboBox entrevistados;
    final TextField Lugar;
    final Button updateInterview;
    final Button removeInterview;

    public ManagementView() {
        this.main = new HorizontalLayout();

        //Setear tablas
        this.tableInterviews = new Table();
        this.tableInterviewers = new Table();
        this.tableIntervieweds = new Table();

        this.setTables();

        //Setear botones
        this.showIntervieweds = new Button("Mostrar entrevistados");
        this.showInterviewers = new Button("Mostrar entrevistadores");
        this.showInterviews = new Button("Mostrar entrevistas");

        this.setUpperButtons();

        rightBar = new VerticalLayout();
        formEntrevistados = new FormLayout();
        formEntrevistadores = new FormLayout();
        formEntrevista = new FormLayout();

        //Crear objectos del primer formulario
        name = new TextField("Nombre");
        apellidos = new TextField("Apellidos");
        DNI = new TextField("DNI");
        nameEntrevistador = new TextField("Nombre");
        apellidosEntrevistador = new TextField("Apellidos");
        DNIEntrevistador = new TextField("DNI");
        Departamento = new TextField("Departamento");
        updateInteviewer = new Button("Modificar");
        updateIntervied = new Button("Modificar");
        removeInteviewer = new Button("Borrar");
        removeIntervied = new Button("Borrar");

        //Crear objectos del segundo formulario
        entrevistadores = new ComboBox("Entrevistador");
        Lugar = new TextField("Lugar");
        entrevistados = new ComboBox("Entrevistado");
        updateInterview = new Button("Modificar");
        removeInterview = new Button("Borrar");

        this.setButtonEvents();
        //Crear formularios
        formEntrevistados.addComponents(name, apellidos, DNI, updateIntervied, removeIntervied);
        formEntrevistadores.addComponents(nameEntrevistador, apellidosEntrevistador, DNIEntrevistador, Departamento, updateInteviewer, removeInteviewer);
        formEntrevista.addComponents(entrevistadores, entrevistados, Lugar, updateInterview, removeInterview);

        formEntrevista.setVisible(false);
        formEntrevistadores.setVisible(false);
        formEntrevistados.setVisible(false);

        rightBar.setVisible(false);
        rightBar.addComponents(formEntrevista, formEntrevistadores, formEntrevistados);
        //Crear vista global
        main.addComponents(new VerticalLayout(getButtons(), tableInterviews, tableInterviewers, tableIntervieweds), rightBar);

    }

    public HorizontalLayout getMain() {
        return main;
    }

    private void setTables() {

        tableInterviews.addContainerProperty("Entrevistador", String.class, null);
        tableInterviews.addContainerProperty("Entrevistado", String.class, null);
        tableInterviews.addContainerProperty("Lugar", String.class, null);
        tableInterviews.addContainerProperty("Fecha", Date.class, null);
        tableInterviews.setVisible(false);

        tableInterviews.addValueChangeListener(e -> {

            rightBar.setVisible(true);
            formEntrevista.setVisible(true);
            formEntrevistadores.setVisible(false);
            formEntrevistados.setVisible(false);
        });

        tableInterviewers.addContainerProperty("Nombre", String.class, null);
        tableInterviewers.addContainerProperty("Apellidos", String.class, null);
        tableInterviewers.addContainerProperty("DNI", String.class, null);
        tableInterviewers.addContainerProperty("Departamento", Date.class, null);

        tableInterviewers.addValueChangeListener(e -> {
            rightBar.setVisible(true);
            formEntrevista.setVisible(false);
            formEntrevistadores.setVisible(true);
            formEntrevistados.setVisible(false);
        });

        tableIntervieweds.addContainerProperty("Nombre", String.class, null);
        tableIntervieweds.addContainerProperty("Apellidos", String.class, null);
        tableIntervieweds.addContainerProperty("DNI", String.class, null);
        tableIntervieweds.setVisible(false);
        tableIntervieweds.addValueChangeListener(e -> {
            rightBar.setVisible(true);
            formEntrevista.setVisible(false);
            formEntrevistadores.setVisible(false);
            formEntrevistados.setVisible(true);
        });

        tableIntervieweds.setSizeFull();
        tableInterviewers.setSizeFull();
        tableInterviews.setSizeFull();
    }

    private void setUpperButtons() {

        showInterviews.addClickListener(e -> {
            tableIntervieweds.setVisible(false);
            tableInterviewers.setVisible(false);
            tableInterviews.setVisible(true);
        });

        showInterviewers.addClickListener(e -> {
            tableIntervieweds.setVisible(false);
            tableInterviewers.setVisible(true);
            tableInterviews.setVisible(false);
        });
        showIntervieweds.addClickListener(e -> {
            tableIntervieweds.setVisible(true);
            tableInterviewers.setVisible(false);
            tableInterviews.setVisible(false);
        });
    }

    public void generateTableInterviews() {

        //Añadir filas aqui
        //obtener lista de base de datos
        //recorrer lista y rellenar tabla
        tableInterviews.addItem(new Object[]{
            "Entrevistador", 
            "Entrevistado",
            "lugar",
            "fecha"
        
        }, 2);

    }

    public void generateTableInterviewers() {

        //Añadir filas aqui
    }

    public void generateTableIntervieweds() {

        //Añadir filas aqui
    }

    private void setButtonEvents() {

        updateInteviewer.addClickListener(e -> {
        });
        updateInteviewer.addClickListener(e -> {
        });
        updateIntervied.addClickListener(e -> {
        });

        removeInteviewer.addClickListener(e -> {
        });
        removeInteviewer.addClickListener(e -> {
        });
        removeIntervied.addClickListener(e -> {
        });

    }

    private HorizontalLayout getButtons() {

        return new HorizontalLayout(showInterviewers, showIntervieweds, showInterviews);
    }
}
