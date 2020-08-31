package ui.views.NotLoggedUserViews;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import control.LoginControl;
import control.SessionControl;
import dao.LoginDAO;
import dto.UserLoginDTO;
import entity.Auto;
import entity.User;
import entity.Vertriebler;
import exceptions.DatabaseException;
import exceptions.NoUserFoundException;
import exceptions.SessionException;
import ui.panel.BottomPanel;
import ui.panel.TopPanel;
import utils.Roles;
import utils.Views;

public class LoginView extends VerticalLayout implements View {

    public void enter(ViewChangeListener.ViewChangeEvent event){

        try {
            SessionControl.controlForNotLoggedSession();
            this.addComponent(new TopPanel());
            this.setUp();
            this.addComponent(new BottomPanel());
        } catch (SessionException e) {
            Notification.show("Error", e.getMessage(), Notification.Type.WARNING_MESSAGE);
        }

    }

    private void setUp(){

        Label Willkommen = new Label("<h1 style=\"text-align: center; color: #000000;\">Willkommen auf CarLook!</h1>\n" +
                "<p>Ihre Nr 1. Suchplattform für Autos auf dem Markt. Registrieren Sie sich heute noch</p>\n" +
                "<p>und profitieren Sie von über 100 Vorteilen als Endkunde und Vertriebler &nbsp;</p>\n" +
                "<!-- Dieser kommentar ist nur im quelltext-editor sichtbar. -->\n" +
                "<p></p>", ContentMode.HTML);

        this.addComponent(Willkommen);
        this.setComponentAlignment(Willkommen, Alignment.MIDDLE_CENTER);

        LoginForm loginForm = new LoginForm();


        loginForm.addLoginListener(e->{
            UserLoginDTO currentUser = new UserLoginDTO();
            currentUser.setUsername(e.getLoginParameter("username"));
            currentUser.setPassword(e.getLoginParameter("password"));

            try{
                LoginControl.checkAuthentication(currentUser);
                UI.getCurrent().getNavigator().navigateTo(Views.MainView);
            }catch (DatabaseException | NoUserFoundException exception){
                Notification.show("Error", exception.getMessage(), Notification.Type.WARNING_MESSAGE);
            }
        });

        VerticalLayout coponentLayout = new VerticalLayout();
        coponentLayout.addComponents(loginForm);
        coponentLayout.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);

        this.addComponent(coponentLayout);
    }

}
