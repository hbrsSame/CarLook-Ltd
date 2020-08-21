package control;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import dao.LoginDAO;
import dto.UserLoginDTO;
import entity.User;
import utils.Roles;

public class LoginControl {

        public static boolean checkAuthentication(UserLoginDTO user){

            User loggedUser = LoginDAO.getInstance().loginCheck(user);
            if(loggedUser != null){
                VaadinSession session = UI.getCurrent().getSession();
                session.setAttribute(Roles.CURRENT_USER, loggedUser);
                return true;
            }else{
                return false;
            }
        }
}
