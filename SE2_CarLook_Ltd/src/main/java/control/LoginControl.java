package control;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import dao.LoginDAO;
import dto.UserLoginDTO;
import entity.User;
import exceptions.DatabaseException;
import exceptions.NoUserFoundException;
import utils.Roles;

public class LoginControl {
    private static User returnedUser;

    public static boolean checkAuthentication(UserLoginDTO user) throws NoUserFoundException, DatabaseException{

        if( (checkUserDataIfEmpty(user) == false ) ) {
            User loggedUser = LoginDAO.getInstance().loginCheck(user);
            return transferUserToDAO(loggedUser, user);
        }else{
            throw new NoUserFoundException("Userdaten ungültig. Bitte gültiten Parameter angeben");
        }
    }


    private static boolean transferUserToDAO(User loggedUser, UserLoginDTO user) throws DatabaseException, NoUserFoundException {
        if(checkUserData(loggedUser, user)){
            if(loggedUser != null){
                returnedUser = loggedUser;
                return true;
            }else{
                throw new DatabaseException("Fehler, angegebene Daten nicht in der Datenbank gefunden!");
            }
        }else{
            throw new NoUserFoundException("Userdaten ungültig! Bitte gültigen Daten angeben!");
        }
    }

    private static boolean checkUserData(User UserDataFromDatabase, UserLoginDTO UserDataFromLogin) throws DatabaseException{
        // Prüfe ob Daten gültige Werte besitzen ( keine Nullwerte, NullPointerException)
        if(UserDataFromDatabase != null){
            return (  UserDataFromDatabase.getUsername().equals( UserDataFromLogin.getUsername() )
                    &&        UserDataFromDatabase.getPassword().equals( UserDataFromLogin.getPassword() )  );
        }else{
            throw new DatabaseException("Userdaten nicht gefunden! Ungültige Daten");
        }
    }


    private static boolean checkUserDataIfEmpty(UserLoginDTO userLoginDTO){
        return  (   userLoginDTO.getUsername().isEmpty() | userLoginDTO.getUsername().length() < 8     | userLoginDTO.getUsername() == null
                &&  userLoginDTO.getPassword().isEmpty() | userLoginDTO.getPassword().length() < 6     | userLoginDTO.getPassword() == null);

    }

    public static User getUser(){
        return returnedUser;
    }
}
