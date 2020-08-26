package control;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import dao.LoginDAO;
import dto.UserLoginDTO;
import entity.User;
import utils.Roles;

public class LoginControl {

        public static boolean checkAuthentication(UserLoginDTO user){

            if( (checkUserDataIfEmpty(user) == false ) ) {
                User loggedUser = LoginDAO.getInstance().loginCheck(user);
                return transferUserToDAO(loggedUser, user);
            }

            return false;
        }


        public static boolean transferUserToDAO(User loggedUser, UserLoginDTO user){
            if(checkUserData(loggedUser, user)){
                if(loggedUser != null){
                    VaadinSession session = UI.getCurrent().getSession();
                    session.setAttribute(Roles.CURRENT_USER, loggedUser);
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }

        private static boolean checkUserData(User UserDataFromDatabase, UserLoginDTO UserDataFromLogin){
            // Prüfe ob Daten gültige Werte besitzen ( keine Nullwerte, NullPointerException)
            if(UserDataFromDatabase.getUsername() != null && UserDataFromDatabase.getPassword() != null){
                        return (  UserDataFromDatabase.getUsername().equals( UserDataFromLogin.getUsername() )
                        &&        UserDataFromDatabase.getPassword().equals( UserDataFromLogin.getPassword() )  );
            }
             return false;
            }


        private static boolean checkUserDataIfEmpty(UserLoginDTO userLoginDTO){
            return  (   userLoginDTO.getUsername().isEmpty() | userLoginDTO.getUsername().length() < 8     | userLoginDTO.getUsername() == null
                    &&  userLoginDTO.getPassword().isEmpty() | userLoginDTO.getPassword().length() < 6     | userLoginDTO.getPassword() == null);

        }
}
