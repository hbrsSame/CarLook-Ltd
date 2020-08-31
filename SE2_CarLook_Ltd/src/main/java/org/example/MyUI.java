package org.example;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import entity.User;
import navigator.home;
import ui.views.LoggedUserViews.*;
import ui.views.NotLoggedUserViews.LoginView;
import ui.views.NotLoggedUserViews.RegisterView;
import utils.Roles;
import utils.Views;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        //Startpage beim Aufruf von localhost:8080

        Navigator pageNavigator = new Navigator(this, this);
        pageNavigator.addView(Views.LoginView, LoginView.class);
        pageNavigator.addView(Views.MainView, MainView.class);
        pageNavigator.addView(Views.RegisterView, RegisterView.class);
        pageNavigator.addView(Views.CreateAutoView, CreateAutoView.class);
        pageNavigator.addView(Views.EingetrageneAutosView, ShowCreatedCarsView.class);
        pageNavigator.addView(Views.ShowBookingView, ShowBookingView.class);
        pageNavigator.addView(Views.BookingCarView, BookingCarView.class);


        //Eine Page als Startpage z.B. LoginView
        User UserInstance = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
        if(UserInstance == null){
            UI.getCurrent().getNavigator().navigateTo(Views.LoginView);
        }else{
            UI.getCurrent().getNavigator().navigateTo(Views.MainView);
        }


    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = home.class, productionMode = true)
    public static class MyUIServlet extends VaadinServlet {
    }
}
