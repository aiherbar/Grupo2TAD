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


    final MainMenu menu = new MainMenu();
    final HorizontalLayout main = new HorizontalLayout(); 
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        //VerticalLayout vertical = new VerticalLayout(menu.getMenu());
        main.addComponents(menu.getMenu(),menu.getCurrent());
        
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

        menu.setSession(session);
        form.addComponents(tituloLogin, usuario, password, login);
        loginLayout.addComponent(form);
        setContent(loginLayout);
        
        addDetachListener((e) -> {
            DBController.getInstance().closeInstance();
        });
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    
}
