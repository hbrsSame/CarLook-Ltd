package ui.views.NotLoggedUserViews;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import control.RegisterControl;
import control.SessionControl;
import entity.Endkunde;
import entity.User;
import entity.Vertriebler;
import exceptions.NoUserFoundException;
import exceptions.RegisterException;
import exceptions.SessionException;
import exceptions.UserAlreadyExistException;
import models.factory.ComponentFactory;
import ui.panel.BottomPanel;
import ui.panel.TopPanel;
import utils.Roles;
import utils.Views;

public class RegisterView extends VerticalLayout implements View {

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        try{
            SessionControl.controlForNotLoggedSession();
            this.addComponent( new TopPanel() );
            this.setup();
            this.addComponent( new BottomPanel() );
        }catch ( SessionException e){
            Notification.show("Error", e.getMessage(), Notification.Type.WARNING_MESSAGE);
        }

    }
    private void setup(){
        TabSheet tabSheet = new TabSheet();
        tabSheet.setWidth("1000");
        tabSheet.addTab(this.createLayoutForEndkunde(Roles.ENDKUNDE), "Registrieren als Endkunde");
        tabSheet.addTab(this.createLayoutForEndkunde(Roles.VERTRIEBLER), "Registrieren als Vertriebler");

        this.addComponent(tabSheet);
        this.setComponentAlignment(tabSheet, Alignment.MIDDLE_RIGHT);
    }

    private VerticalLayout createLayoutForEndkunde(String type){
        VerticalLayout EndkundeLayout = new VerticalLayout();

        TextField username = ComponentFactory.createTextFieldWithCaption("E-mail/Username");
        PasswordField password = ComponentFactory.createPasswordFieldWithCaption("Password");
        PasswordField repeatPassword = ComponentFactory.createPasswordFieldWithCaption("Password wiederholen");
        TextField name = ComponentFactory.createTextFieldWithCaption("Name");
        Button registerButton = ComponentFactory.createButtonWithCaption("Jetzt regestrieren");

        String valueOfName = name.getValue();

        registerButton.addClickListener(e->{

            User user = null;
            if(type.equals("Endkunde")){
                user = new Endkunde();

                user.setUsername(username.getValue());
                user.setPassword(password.getValue());
                user.setRepeatPassword(repeatPassword.getValue());
                ((Endkunde) user).setName(name.getValue());

            }
            if(type.equals("Vertriebler")){
                user = new Vertriebler();

                user.setUsername(username.getValue());
                user.setPassword(password.getValue());
                user.setRepeatPassword(repeatPassword.getValue());
                ((Vertriebler) user).setName(name.getValue());
            }


            try {
                if(RegisterControl.registerUser(user) ) {
                    Notification.show("Sucess", "Hallo " + valueOfName + "! Sie haben sich erfolgreich regestriert! Wilkommen bei CarLook",
                    Notification.Type.WARNING_MESSAGE);
                    UI.getCurrent().getNavigator().navigateTo(Views.LoginView);
                }
            } catch (RegisterException | NoUserFoundException | UserAlreadyExistException exception) {
                Notification.show("Error", exception.getMessage(), Notification.Type.WARNING_MESSAGE);
            }

        });

        EndkundeLayout.addComponents(username,password,repeatPassword,name,registerButton);
        EndkundeLayout.setComponentAlignment(username, Alignment.MIDDLE_LEFT);
        EndkundeLayout.setComponentAlignment(password, Alignment.MIDDLE_LEFT);
        EndkundeLayout.setComponentAlignment(repeatPassword, Alignment.MIDDLE_LEFT);
        EndkundeLayout.setComponentAlignment(name, Alignment.MIDDLE_LEFT);
        EndkundeLayout.setComponentAlignment(registerButton, Alignment.MIDDLE_LEFT);

        return EndkundeLayout;
    }


}
