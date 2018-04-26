package com.mycompany.grupo2tad;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
    
    final HorizontalLayout main = new HorizontalLayout();
    final MainMenu menu = new MainMenu();
    final TopAndLeftMenu top = new TopAndLeftMenu();
    
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {

        
        main.addComponent(menu.getMenu());
        Calendar cal = new Calendar();
        cal.setWidth(100, Unit.PERCENTAGE);
        
        main.addComponents(new VerticalLayout(top.getTopBar(), cal));

//        final VerticalLayout layout = new VerticalLayout();
//        MainMenu menu = new MainMenu();
//        final TextField name = new TextField();
//        name.setCaption("Type your name here:");
//
//        Button button = new Button("Click Me");
//        button.addClickListener( e -> {
//            layout.addComponent(new Label("Thanks " + name.getValue() 
//                    + ", it works!"));
//        });
//        
//        layout.addComponents(menu.getBarmenu(),name, button);
//        layout.setMargin(true);
//        layout.setSpacing(true);
//        
        setContent(main);
    }
    
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

}
