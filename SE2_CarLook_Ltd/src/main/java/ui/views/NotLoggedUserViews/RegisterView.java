package ui.views.NotLoggedUserViews;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import control.SessionControl;
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
            
        });


        return EndkundeLayout;
    }


}
