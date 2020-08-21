package control;

import com.vaadin.ui.UI;
import entity.User;
import utils.Roles;

public class SessionControl {

    User validUserSesion = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
}
