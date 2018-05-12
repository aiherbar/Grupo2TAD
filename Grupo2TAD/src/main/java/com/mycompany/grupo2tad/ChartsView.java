/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.grupo2tad;

import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;

/**
 *
 * @author Anais
 */
public class ChartsView {
        final HorizontalLayout main;
        final Grid grid;
        
    public ChartsView() {
        this.main = new HorizontalLayout();
        this.grid = new Grid();
        main.addComponent(grid);
    }

    public HorizontalLayout getMain() {
        return main;
    }
        
        
}
