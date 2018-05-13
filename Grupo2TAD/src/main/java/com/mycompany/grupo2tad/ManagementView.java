/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo2tad;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
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
    final TextField apto;
    final Button updateInterview;
    final Button removeInterview;

    private int id;

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
        apto = new TextField("Apto (SI/NO)");
        updateInterview = new Button("Modificar");
        removeInterview = new Button("Borrar");

        this.setButtonEvents();
        //Crear formularios
        formEntrevistados.addComponents(name, apellidos, DNI, updateIntervied, removeIntervied);
        formEntrevistadores.addComponents(nameEntrevistador, apellidosEntrevistador, DNIEntrevistador, Departamento, updateInteviewer, removeInteviewer);
        formEntrevista.addComponents(apto, updateInterview, removeInterview);

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

        tableInterviews.addContainerProperty("Entrevistador", Object.class, null);
        tableInterviews.addContainerProperty("Entrevistado", Object.class, null);
        tableInterviews.addContainerProperty("Lugar", String.class, null);
        tableInterviews.addContainerProperty("Fecha", Date.class, null);
        tableInterviews.addContainerProperty("Apto", String.class, null);
        tableInterviews.setVisible(false);

        tableInterviews.addItemClickListener(e -> {

            rightBar.setVisible(true);
            formEntrevista.setVisible(true);
            formEntrevistadores.setVisible(false);
            formEntrevistados.setVisible(false);
            id = (int) tableInterviews.getValue();
            apto.setValue((String) e.getItem().getItemProperty("Apto").getValue());

        });

        tableInterviewers.addContainerProperty("Nombre", String.class, null);
        tableInterviewers.addContainerProperty("Apellidos", String.class, null);
        tableInterviewers.addContainerProperty("DNI", String.class, null);
        tableInterviewers.addContainerProperty("Departamento", Date.class, null);

        tableInterviewers.addItemClickListener(e -> {
            rightBar.setVisible(true);
            formEntrevista.setVisible(false);
            formEntrevistadores.setVisible(true);
            formEntrevistados.setVisible(false);
            id = (int) tableInterviewers.getValue();

            nameEntrevistador.setValue((String) e.getItem().getItemProperty("Nombre").getValue());
            apellidosEntrevistador.setValue((String) e.getItem().getItemProperty("Apellidos").getValue());
            DNIEntrevistador.setValue((String) e.getItem().getItemProperty("DNI").getValue());
            Departamento.setValue((String) e.getItem().getItemProperty("Departamento").getValue());
        });

        tableIntervieweds.addContainerProperty("Nombre", String.class, null);
        tableIntervieweds.addContainerProperty("Apellidos", String.class, null);
        tableIntervieweds.addContainerProperty("DNI", String.class, null);
        tableIntervieweds.setVisible(false);
        tableIntervieweds.addItemClickListener(e -> {
            rightBar.setVisible(true);
            formEntrevista.setVisible(false);
            formEntrevistadores.setVisible(false);
            formEntrevistados.setVisible(true);
            id = (int) tableIntervieweds.getValue();

            name.setValue((String) e.getItem().getItemProperty("Nombre").getValue());
            apellidos.setValue((String) e.getItem().getItemProperty("Apellidos").getValue());
            DNI.setValue((String) e.getItem().getItemProperty("DNI").getValue());
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
            String name = nameEntrevistador.getValue();
            String surname = apellidosEntrevistador.getValue();
            String dni = DNIEntrevistador.getValue();
            String departamento = Departamento.getValue();

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
