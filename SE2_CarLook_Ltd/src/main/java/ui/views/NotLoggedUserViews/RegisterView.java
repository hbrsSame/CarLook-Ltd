package ui.views.NotLoggedUserViews;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import control.RegisterControl;
import control.SessionControl;
import entity.Endkunde;
import exceptions.RegisterException;
import exceptions.SessionException;
import models.factory.ComponentFactory;
import ui.panel.BottomPanel;
import ui.panel.TopPanel;

public class RegisterView extends VerticalLayout {

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        try{
            SessionControl.controlForNotLoggedSession();
            this.addComponent( new TopPanel() );
            this.setup();
            this.addComponent( new BottomPanel() );
        }catch ( SessionException e){
            Notification.show("Error", e.getMessages(), Notification.Type.WARNING_MESSAGE);
        }

    }
    private void setup(){
        TabSheet tabSheet = new TabSheet();
        tabSheet.setWidth("1000");


    }

    private VerticalLayout createLayoutForEndkunde(){
        VerticalLayout EndkundeLayout = new VerticalLayout();

        TextField username = ComponentFactory.createTextFieldWithCaption("E-mail/Username");
        PasswordField password = ComponentFactory.createPasswordFieldWithCaption("Password");
        PasswordField repeatPassword = ComponentFactory.createPasswordFieldWithCaption("Password wiederholen");
        TextField name = ComponentFactory.createTextFieldWithCaption("Name");
        Button registerButton = ComponentFactory.createButtonWithCaption("Jetzt regestrieren");

        registerButton.addClickListener(e->{
            Endkunde user = new Endkunde();

            user.setUsername(username.getValue());
            user.setPassword(password.getValue());
            user.setRepeatPassword(repeatPassword.getValue());
            user.setName(name.getValue());

            try {
                if(RegisterControl.registerUser(user) ) {
                    Notification.show("Sucess", "Hallo " + user.getName() + "! Sie haben sich erfolgreich regestriert! Wilkommen bei CarLook",
                    Notification.Type.WARNING_MESSAGE);
                }
            } catch (RegisterException registerException) {
                Notification.show("Error", registerException.getMessages(), Notification.Type.WARNING_MESSAGE);
            }

        });

        EndkundeLayout.addComponents(username,password,repeatPassword,name,registerButton);
        EndkundeLayout.setComponentAlignment(username, Alignment.MIDDLE_CENTER);
        EndkundeLayout.setComponentAlignment(password, Alignment.MIDDLE_CENTER);
        EndkundeLayout.setComponentAlignment(repeatPassword, Alignment.MIDDLE_CENTER);
        EndkundeLayout.setComponentAlignment(name, Alignment.MIDDLE_CENTER);
        EndkundeLayout.setComponentAlignment(registerButton, Alignment.MIDDLE_CENTER);

        return EndkundeLayout;
    }


}
