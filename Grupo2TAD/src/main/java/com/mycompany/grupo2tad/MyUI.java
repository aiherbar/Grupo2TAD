package com.mycompany.grupo2tad;

import com.vaadin.annotations.PreserveOnRefresh;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Calendar;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.hibernate.Session;
import proyecto_tad.util.HibernateUtil;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@PreserveOnRefresh
@Theme("mytheme")
public class MyUI extends UI {

    final HorizontalLayout main = new HorizontalLayout();
    final MainMenu menu = new MainMenu();
    final TopAndRightMenu top = new TopAndRightMenu();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        WrappedSession session = getSession().getSession();
     
        CssLayout loginLayout = new CssLayout();
        FormLayout form = new FormLayout();

        final Label tituloLogin = new Label("<h2>Login</h2>", ContentMode.HTML);
        final TextField usuario = new TextField("Usuario");
        final TextField password = new TextField("Contraseña");

        Button login = new Button("Acceder");
        login.addClickListener(e -> {
            if (session.getAttributeNames().contains("usuario") || (usuario.getValue().equals("admin") && password.getValue().equals("admin"))) {
                setContent(main);
                session.setAttribute("usuario", "admin");
            }
        });

        form.addComponents(tituloLogin, usuario, password, login);
        loginLayout.addComponent(form);
        setContent(loginLayout);
        
        main.addComponent(menu.getMenu());
        Calendar cal = new Calendar();
        java.util.Calendar c = java.util.Calendar.getInstance();
        cal.setStartDate(c.getTime());
        c.add(java.util.Calendar.DAY_OF_MONTH, 30);
        cal.setEndDate(c.getTime());
        cal.setWidth(100, Unit.PERCENTAGE);
        
        main.addComponents(new VerticalLayout(top.getTopBar(), cal),top.getRightBar());

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
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    
}
