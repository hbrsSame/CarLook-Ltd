package navigator;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import ui.views.NotLoggedUserViews.LoginView;
import ui.views.NotLoggedUserViews.MainView;
import utils.Views;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Title("CarLook Ltd")
public class home extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        //Startpage beim Aufruf von localhost:8080

        Navigator pageNavigator = new Navigator(this, this);
        pageNavigator.addView(Views.LoginView, LoginView.class);
        pageNavigator.addView(Views.MainView, MainView.class);


        //Eine Page als Startpage z.B. LoginView
        UI.getCurrent().getNavigator().navigateTo(Views.MainView);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = home.class, productionMode = true)
    public static class MyUIServlet extends VaadinServlet {
    }
}
