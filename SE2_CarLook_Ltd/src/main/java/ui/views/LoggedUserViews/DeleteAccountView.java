package ui.views.LoggedUserViews;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import control.RegisterControl;
import control.SessionControl;
import dao.KeyDAO;
import dto.UserLoginDTO;
import entity.Endkunde;
import entity.User;
import exceptions.NoUserFoundException;
import exceptions.SessionException;
import models.builder.UserBuilder;
import models.factory.ComponentFactory;
import utils.Roles;

public class DeleteAccountView extends VerticalLayout implements View {

    public void enter(ViewChangeListener.ViewChangeEvent event)  {
        try {
            SessionControl.controlForLoggedUsers();
            this.setUp();
        } catch (SessionException e) {
            Notification.show("Error", "Diese Seite ist nicht für Sie gedacht!", Notification.Type.WARNING_MESSAGE);
        }

    }

    public void setUp(){
        Label info = new Label("Username eingeben und auf Löschen klicken, um den User zu entfernen");
        TextField username = ComponentFactory.createTextFieldWithCaption("Username");
        username.setId("username");
        TextField type = ComponentFactory.createTextFieldWithCaption("Typ");
        type.setId("type");
        Button button = ComponentFactory.createButtonWithCaption("Löschen");
        button.setId("löschen");


        button.addClickListener(e->{
            if(type.getValue().equals("Endkunde")){
                UserLoginDTO login = new UserLoginDTO();
                login.setUsername(username.getValue());

                int endkundenPK = KeyDAO.getPKFromEndkunde(login);

                if(endkundenPK == 0){
                    Notification.show("Errro", "User bereits gelöscht", Notification.Type.WARNING_MESSAGE);
                    return;
                }
                User endkunde = new UserBuilder(Roles.ENDKUNDE)
                        .mitUsername(username.getValue())
                        .mitSpecificID(endkundenPK)
                        .createUser();

                try {
                    RegisterControl.DeleteUser(endkunde);
                    Notification.show("Sucess", "User " + username.getValue() + "  wurde erfolgreich gelöscht", Notification.Type.WARNING_MESSAGE);

                } catch (NoUserFoundException noUserFoundException) {
                    Notification.show("Error", noUserFoundException.getMessage(), Notification.Type.WARNING_MESSAGE);
                }

            }else{
                Notification.show("Typ muss Endkunde sein. Es kann momentan nur ein Endkunde gelöscht werden");
                type.setValue("");
            }
        });

        this.addComponents(info, username, type,  button);
        this.setComponentAlignment(username, Alignment.MIDDLE_CENTER);
        this.setComponentAlignment(info, Alignment.MIDDLE_CENTER);
        this.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
        this.setComponentAlignment(type, Alignment.MIDDLE_CENTER);
    }
}
