package ui.panel;

import com.vaadin.server.FileResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import entity.Endkunde;
import entity.User;
import entity.Vertriebler;
import utils.Path;
import utils.Roles;
import utils.Views;

import java.io.File;

public class TopPanel extends VerticalLayout {

    public TopPanel(){

        FileResource logoResource = new FileResource( new File( Path.BASEPATH + "/2520.jpg"));
        Image logo = new Image(" ", logoResource);
        //---Menu Bar---
        MenuBar menuBar = new MenuBar();
        menuBar.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        menuBar.setAutoOpen(true);
        MenuBar.MenuItem loggedUserMenuItem = null;

        Label angemeldetAls = new Label( "Melden Sie sich an!");

        User validUser = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
        if(validUser != null){
            loggedUserMenuItem.addItem("Mein Konto");

            if(validUser instanceof Endkunde) {
                angemeldetAls = new Label("angemeldet als: " + ((Endkunde) validUser).getName());
                loggedUserMenuItem.addItem("Autos suchen", FontAwesome.SEARCH, e ->{
                     UI.getCurrent().getNavigator().navigateTo(Views.SucheAutosView);
                });

                loggedUserMenuItem.addItem("Autos reservieren", FontAwesome.BOOK, e->{
                    UI.getCurrent().getNavigator().navigateTo(Views.ReserviereAutoView);
                });

                loggedUserMenuItem.addItem("Reservierungen anzeigen", FontAwesome.DASHBOARD, e->{
                    UI.getCurrent().getNavigator().navigateTo(Views.ReservierungenView);
                });
            }

            if(validUser instanceof Vertriebler){
                angemeldetAls = new Label("angemeldet als: " + ((Vertriebler) validUser).getName());
                loggedUserMenuItem.addItem("Autos eintragen", FontAwesome.REGISTERED, e->{
                    UI.getCurrent().getNavigator().navigateTo(Views.AutosEintragenView);
                });

                loggedUserMenuItem.addItem("Eingetragen Autos", FontAwesome.DATABASE, e->{
                    UI.getCurrent().getNavigator().navigateTo(Views.EingetrageneAutosView);
                });
            }

        }else{
            menuBar.addItem("Registrieren",FontAwesome.REGISTERED, e -> {
                UI.getCurrent().getNavigator().navigateTo(Views.RegisterView);
            });
            menuBar.addItem("Login", FontAwesome.SIGN_IN, e ->{
                UI.getCurrent().getNavigator().navigateTo(Views.LoginView);
            });
        }

        VerticalLayout a = new VerticalLayout();
        a.addComponent(logo);
        a.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
        a.setSizeFull();
        HorizontalLayout topMenuBar = new HorizontalLayout();
        topMenuBar.addComponent(angemeldetAls);
        topMenuBar.addComponent(menuBar);
        topMenuBar.setComponentAlignment(menuBar, Alignment.MIDDLE_CENTER);
        topMenuBar.setComponentAlignment(angemeldetAls, Alignment.MIDDLE_CENTER);

        this.addComponent(a);
        this.setComponentAlignment(a, Alignment.MIDDLE_CENTER);
        this.addComponent(topMenuBar);
        this.setComponentAlignment(topMenuBar,Alignment.MIDDLE_CENTER);
    }


}
