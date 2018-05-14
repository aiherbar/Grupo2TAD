/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo2tad;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.LayoutDirection;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import proyecto_tad.entity.Entrevistador;

/**
 *
 * @author Anais
 */
public class ChartsView {

    final VerticalLayout main;
    DBController controller;

    public ChartsView() {
        controller = DBController.getInstance();
        this.main = new VerticalLayout();

        main.addComponents(this.getColumChart(),this.getPieChart());
    }

    public VerticalLayout getMain() {
        return main;
    }

    protected Chart getColumChart(){
        Chart chart = new Chart(ChartType.COLUMN);
        Configuration conf = chart.getConfiguration();
        conf.setTitle("Entrevistas aptas y no aptas por entrevistador");
        XAxis x = new XAxis();
        
        ListSeries aptos = new ListSeries("Aptos");
        ListSeries noAptos = new ListSeries("No Aptos");
        
        List<Entrevistador> entrevistadores = controller.getEntrevistadores();
        for (Entrevistador entrevistador : entrevistadores) {
            x.addCategory(entrevistador.getNombre()+"("+entrevistador.getDni()+")");
            aptos.addData(controller.getAptosEntrevistador(entrevistador.getId()));
            noAptos.addData(controller.getNoAptosInvestigador(entrevistador.getId()));
        }

        conf.addxAxis(x);
        
        YAxis y = new YAxis();
        y.setMin(0);
        y.setTitle("Candidatos");
        conf.addyAxis(y);
        
        Legend legend = new Legend();
        legend.setLayout(LayoutDirection.VERTICAL);
        legend.setBackgroundColor(new SolidColor("#FFFFFF"));
        legend.setAlign(HorizontalAlign.LEFT);
        legend.setVerticalAlign(VerticalAlign.TOP);
        legend.setX(100);
        legend.setY(70);
        legend.setFloating(true);
        legend.setShadow(true);
        conf.setLegend(legend);
        
        
        conf.addSeries(aptos);
        conf.addSeries(noAptos);
        
        chart.drawChart(conf);
        return chart;
    }
    
    public Chart getPieChart(){
        Chart chart = new Chart(ChartType.PIE);
 
        Configuration conf = chart.getConfiguration();
 
        conf.setTitle("Porcentaje de aptos y no aptos.");
 
        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setCursor(Cursor.POINTER);
        DataLabels dataLabels = new DataLabels();
        dataLabels.setEnabled(true);
        dataLabels.setFormatter("'<b>'+ this.point.name +'</b>: '+ this.percentage +' %'");
        plotOptions.setDataLabels(dataLabels);
        conf.setPlotOptions(plotOptions);
        
        final DataSeries series = new DataSeries();
        series.add(new DataSeriesItem("Aptos", controller.getAptos()));
        DataSeriesItem noAptos = new DataSeriesItem("No aptos", controller.getNoAptos());
        noAptos.setSliced(true);
        noAptos.setSelected(true);
        series.add(noAptos);
        conf.setSeries(series);
        
        chart.drawChart(conf);
 
        return chart;
    }
}
