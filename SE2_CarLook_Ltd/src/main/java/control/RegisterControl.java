package control;

import dao.RegisterDAO;
import entity.User;
import exceptions.NoUserFoundException;
import exceptions.RegexException;
import exceptions.RegisterException;
import exceptions.UserAlreadyExistException;
import models.service.RegexChecker;

public class RegisterControl {

    private static String errorMessages = "";
    public static boolean registerUser(User user) throws RegisterException, NoUserFoundException, UserAlreadyExistException, RegexException {

        if(user == null){
            throw new NoUserFoundException("Userdaten dürfen nicht leer sein");
        }

        if(RegisterDAO.getInstance().checkIfUserAlreadyExist(user)){
            throw new UserAlreadyExistException("User bereits vorhanden");
        }

        if(checkUserData(user) ) {
            try{
                RegexChecker.checkEmail(user.getUsername());
                return RegisterDAO.getInstance().registerUser(user);
            }catch (RegexException regexException){
                throw new RegexException("Die Subdomain muss @carlook.de sein. Bitte korrigieren Sie Ihre Eingabe");
            }

        }

        if(checkUserData(user) == false){
            String tmpMessages = errorMessages;
            errorMessages = "";
            throw new RegisterException("Fehlermeldung: " + tmpMessages);
        }

        return false;


    }

    private static boolean checkUserData( User user){
        boolean checkUsername = true;
        boolean checkPassword = true;
        boolean checkRepeatPassword = true;
        if(user.getUsername().isEmpty() || user.getUsername().length() < 8){
            checkUsername = false;
            errorMessages+= " Username zu kurz oder leer. Der Username muss größer als 8 Zeichen sein";
        }

        if(user.getPassword().isEmpty() || user.getPassword().length() < 8){
            checkPassword = false;
            errorMessages+= " Password zu kurz oder leer. Das Password muss größer als 8 Zeichen sein";
        }

        if(  !(user.getRepeatPassword().equals( user.getPassword() )  ) ){
            checkRepeatPassword = false;
            errorMessages+= " Passwörter nicht identisch!";
        }

        return checkUsername && checkPassword && checkRepeatPassword;
    }

}
