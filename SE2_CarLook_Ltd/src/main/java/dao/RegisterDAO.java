package dao;

import entity.Endkunde;
import entity.User;
import entity.Vertriebler;

public class RegisterDAO {

    private static RegisterDAO instance = null;

    private RegisterDAO () {}

    public static RegisterDAO getInstance(){
        if(instance == null){
            instance = new RegisterDAO();
        }
        return instance;
    }


    public boolean registerUser(User user){

        if ( user instanceof Endkunde){
            return this.registerEndkunde(user);
        }

        if ( user instanceof Vertriebler){
            return this.registerVertriebler(user);
        }

        return false;
    }

    private boolean registerEndkunde(User user){


        return false;
    }

    private boolean registerVertriebler(User user){


        return false;
    }




}
