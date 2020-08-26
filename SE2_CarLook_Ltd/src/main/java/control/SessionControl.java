package control;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.UI;
import entity.Endkunde;
import entity.User;
import entity.Vertriebler;
import exceptions.SessionException;
import utils.Roles;
import utils.Views;

public class SessionControl {

    private static User validUserSesion = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);


    public static boolean controlForNotLoggedSession() throws SessionException{
        boolean value = true;
        if(validUserSesion != null){
            value = false;
            throw new SessionException("Sie sind bereits eingeloggt! Bitte loggen Sie sich aus, um diese Seite zu sehen!");
        }
        return value;
    }

    public static boolean controlLoggedSessionForEndkunde() {
        boolean value = false;

        if(validUserSesion instanceof Endkunde ){
            value = true;
        }
        return value;
    }

    public static boolean controlLoggedSessionForVertriebler() {
        boolean value = false;

        if(validUserSesion instanceof Vertriebler){
            value = true;
        }
        return value;
    }

    public static boolean controlForLoggedUsers() throws SessionException {
        boolean value = true;
        if(validUserSesion == null) {
            UI.getCurrent().getNavigator().navigateTo(Views.LoginView);
            value = false;
            throw new SessionException("Sie sind nicht eingeloggt! Loggen Sie sich ein um diese Seite zu sehen");
        }
        return value;
    }
}
