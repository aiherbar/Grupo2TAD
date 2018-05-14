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
import proyecto_tad.entity.Entrevista;
import proyecto_tad.entity.Entrevistado;
import proyecto_tad.entity.Entrevistador;

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
    final TextField DNI;
    final TextField nameEntrevistador;
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
    private DBController controller;

    public ManagementView() {
        controller = DBController.getInstance();
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
        DNI = new TextField("DNI");
        nameEntrevistador = new TextField("Nombre");
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
        formEntrevistados.addComponents(name, DNI, updateIntervied, removeIntervied);
        formEntrevistadores.addComponents(nameEntrevistador, DNIEntrevistador, Departamento, updateInteviewer, removeInteviewer);
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

        //Entrevistas
        tableInterviews.addContainerProperty("Entrevistador", Object.class, null);
        tableInterviews.addContainerProperty("Entrevistado", Object.class, null);
        tableInterviews.addContainerProperty("Lugar", String.class, null);
        tableInterviews.addContainerProperty("Fecha", Date.class, null);
        tableInterviews.addContainerProperty("Apto", String.class, null);
        tableInterviews.addContainerProperty("Id", Integer.class, null);

        tableInterviews.addItemClickListener(e -> {
            rightBar.setVisible(true);
            formEntrevista.setVisible(true);
            formEntrevistadores.setVisible(false);
            formEntrevistados.setVisible(false);
            id = (Integer) e.getItem().getItemProperty("Id").getValue();
            apto.setValue((String) e.getItem().getItemProperty("Apto").getValue());

        });

        //Entrevistadores
        tableInterviewers.addContainerProperty("Nombre", String.class, null);
        tableInterviewers.addContainerProperty("DNI", String.class, null);
        tableInterviewers.addContainerProperty("Departamento", String.class, null);
        tableInterviewers.addContainerProperty("Id", Integer.class, null);
        tableInterviewers.addItemClickListener(e -> {
            rightBar.setVisible(true);
            formEntrevista.setVisible(false);
            formEntrevistadores.setVisible(true);
            formEntrevistados.setVisible(false);
            id = (Integer) e.getItem().getItemProperty("Id").getValue();

            nameEntrevistador.setValue((String) e.getItem().getItemProperty("Nombre").getValue());
            DNIEntrevistador.setValue((String) e.getItem().getItemProperty("DNI").getValue());
            Departamento.setValue((String) e.getItem().getItemProperty("Departamento").getValue());
        });

        //Entrevistados
        tableIntervieweds.addContainerProperty("Nombre", String.class, null);
        tableIntervieweds.addContainerProperty("DNI", String.class, null);
        tableIntervieweds.addContainerProperty("Id", Integer.class, null);
        tableIntervieweds.addItemClickListener(e -> {
            rightBar.setVisible(true);
            formEntrevista.setVisible(false);
            formEntrevistadores.setVisible(false);
            formEntrevistados.setVisible(true);
            id = (Integer) e.getItem().getItemProperty("Id").getValue();

            name.setValue((String) e.getItem().getItemProperty("Nombre").getValue());
            DNI.setValue((String) e.getItem().getItemProperty("DNI").getValue());
        });
//Configuracion de las tablas
        tableIntervieweds.setVisible(false);
        tableInterviewers.setVisible(false);
        tableInterviews.setVisible(true);

        tableIntervieweds.setSelectable(true);
        tableInterviewers.setSelectable(true);
        tableInterviews.setSelectable(true);

        tableIntervieweds.setSizeFull();
        tableInterviewers.setSizeFull();
        tableInterviews.setSizeFull();

        tableIntervieweds.setImmediate(true);
        tableInterviewers.setImmediate(true);
        tableInterviews.setImmediate(true);
        //Generar datos
        this.generateTableIntervieweds();
        this.generateTableInterviewers();
        this.generateTableInterviews();
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
//        tableInterviews.setVisibleColumns("Entrevistador", "Entrevistado", "Lugar", "Fecha", "Apto", "Id");
        tableInterviews.removeAllItems();
        for (Entrevista entrevista : controller.getEntrevistas()) {
            tableInterviews.addItem(new Object[]{
                controller.getEntrevistador(entrevista.getIdEntrevistador()),
                controller.getEntrevistado(entrevista.getIdEntrevistado()),
                entrevista.getLugar(),
                entrevista.getFecha(),
                entrevista.getApto(),
                entrevista.getId()

            }, entrevista.getId());

        }
       // tableInterviews.setVisibleColumns("Entrevistador", "Entrevistado", "Lugar", "Fecha", "Apto");

    }

    public void generateTableInterviewers() {

        //Añadir filas aqui
//        tableInterviewers.setVisibleColumns("Nombre", "DNI", "Departamento", "Id");
        tableInterviewers.removeAllItems();
        for (Entrevistador entrevistador : controller.getEntrevistadores()) {
            tableInterviewers.addItem(new Object[]{
                entrevistador.getNombre(),
                entrevistador.getDni(),
                entrevistador.getDepartamento(),
                entrevistador.getId()
            }, entrevistador.getId());
        }
        //tableInterviewers.setVisibleColumns("Nombre", "DNI", "Departamento");
    }

    public void generateTableIntervieweds() {

        //Añadir filas aqui
//        tableIntervieweds.setVisibleColumns("Nombre", "DNI", "Id");
        tableIntervieweds.removeAllItems();
        for (Entrevistado entrevistado : controller.getEntrevistados()) {
            tableIntervieweds.addItem(new Object[]{
                entrevistado.getNombre(),
                entrevistado.getDni(),
                entrevistado.getId()
            }, entrevistado.getId());
        }
       // tableIntervieweds.setVisibleColumns("Nombre", "DNI");
    }

    private void setButtonEvents() {

        updateInteviewer.addClickListener(e -> {
            String name = nameEntrevistador.getValue();
            String dni = DNIEntrevistador.getValue();
            String departamento = Departamento.getValue();
   
            controller.updateEntrevistador(id, dni, name, departamento);
           this.generateTableInterviewers();

        });
        updateInterview.addClickListener(e -> {
            String apto = this.apto.getValue();
            controller.updateEntrevistaApto(id, apto);
           this.generateTableInterviews();
        });
        updateIntervied.addClickListener(e -> {
            String name = this.name.getValue();
            String dni = this.DNI.getValue();
            controller.updateEntrevistado(id, dni, name);
            this.generateTableIntervieweds();
        });

        removeInteviewer.addClickListener(e -> {
            controller.deleteEntrevistador(id);
           this.generateTableInterviewers();
        });
        removeInterview.addClickListener(e -> {
            controller.deleteEntrevista(id);
          this.generateTableInterviews();
        });
        removeIntervied.addClickListener(e -> {
            controller.deleteEntrevistado(id);
           this.generateTableIntervieweds();
        });

    }

    private HorizontalLayout getButtons() {

        return new HorizontalLayout(showInterviewers, showIntervieweds, showInterviews);
    }
}
