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

        FileResource logoResource = new FileResource( new File( Path.BASEPATH + "/Carlook2.png"));
        Image logo = new Image(" ", logoResource);
        logo.setWidth("1350");
        logo.setHeight("503");

        logo.addClickListener( e->UI.getCurrent().getNavigator().navigateTo(Views.MainView));
        //---Menu Bar---
        MenuBar menuBar = new MenuBar();
        menuBar.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        menuBar.setAutoOpen(true);
        MenuBar.MenuItem loggedUserMenuItem;

        Label angemeldetAls = new Label( "Melden Sie sich an!");

        try{
            User validUser = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
            if(validUser != null){

                loggedUserMenuItem = menuBar.addItem("Mein Konto");

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

                menuBar.addItem("Logout", FontAwesome.SIGN_OUT, e->{
                    UI.getCurrent().getNavigator().navigateTo(Views.LoginView);
                    UI.getCurrent().getSession().close();
                    UI.getCurrent().getPage().reload();
                });

            }else{
                menuBar.addItem("Registrieren",FontAwesome.REGISTERED, e -> {
                    UI.getCurrent().getNavigator().navigateTo(Views.RegisterView);
                });
                menuBar.addItem("Login", FontAwesome.SIGN_IN, e ->{
                    UI.getCurrent().getNavigator().navigateTo(Views.LoginView);
                });

            }

        }catch ( Exception e){
            Notification.show("Error", e.getMessage(), Notification.Type.WARNING_MESSAGE);
        }


        VerticalLayout a = new VerticalLayout();
        a.addComponent(logo);
        a.setComponentAlignment(logo, Alignment.MIDDLE_CENTER);
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
