package control;

import dao.RegisterDAO;
import entity.User;
import exceptions.RegisterException;

public class RegisterControl {

    public static boolean registerUser(User user) throws RegisterException{

        if(user == null){
            return false;
        }

        if(checkUser(user) ){
            return RegisterDAO.getInstance().registerUser(user);
        }

        if(checkUser(user) == false){
            throw new RegisterException("Registrierung fehlgeschlagen");
        }

        return false;
    }

    private static boolean checkUser( User user){
        boolean checkUsername = true;
        boolean checkPassword = true;
        boolean checkRepeatPassword = true;
        if(user.getUsername().isEmpty() && user.getUsername().length() < 8){
            checkUsername = false;
        }

        if(user.getPassword().isEmpty() && user.getPassword().length() < 8){
            checkPassword = false;
        }

        if(user.getRepeatPassword() != user.getPassword() ){
            checkRepeatPassword = false;
        }

        return checkUsername && checkPassword && checkRepeatPassword;
    }
}
