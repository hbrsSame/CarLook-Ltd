package dao;

import database.JDBC;
import dto.UserLoginDTO;
import entity.Endkunde;
import entity.User;
import entity.Vertriebler;
import exceptions.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dao.KeyDAO.getPKFromUser;
import static dao.KeyDAO.getPKFromVertriebler;

public class LoginDAO {

    private static LoginDAO instance;

    private LoginDAO() {}

    public static LoginDAO getInstance(){
        if(instance == null){
            return new LoginDAO();
        }
        return instance;
    }

    public User loginCheck(UserLoginDTO user){
        User validEndkunde = returnEndkunde(user);
        User validVertriebler = returnVertriebler(user);

        if(validEndkunde != null){
            return validEndkunde;
        }

        if(validVertriebler != null){
            return validVertriebler;
        }
        return null;

    }

    private User returnVertriebler(UserLoginDTO user){
        PreparedStatement data = null;
        Vertriebler vertriebler = new Vertriebler();
        try {
            data = JDBC.getInstance().getPreparedStatement("SELECT * FROM carlook.user WHERE username = ?");
            data.setString(1, user.getUsername());
            ResultSet result = data.executeQuery();

            while(result.next()){

                vertriebler.setUsername(result.getString(1));
                vertriebler.setPassword(result.getString(2));
            }

            data = JDBC.getInstance().getPreparedStatement("SELECT * FROM carlook.vertriebler WHERE username_fk = ?");
            data.setString(1, getPKFromUser(user.getUsername()));
            result = data.executeQuery();

            while(result.next()){
                vertriebler.setName(result.getString(2));
                vertriebler.setVertrieblerID(result.getInt(1));
            }

            return vertriebler;

        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private  User returnEndkunde(UserLoginDTO user){
        PreparedStatement data = null;
        Endkunde kunde = new Endkunde();
        try {
            data = JDBC.getInstance().getPreparedStatement("SELECT * FROM carlook.user WHERE username = ?");
            data.setString(1, user.getUsername());
            ResultSet result = data.executeQuery();

            while(result.next()){

                kunde.setUsername(result.getString(1));
                kunde.setPassword(result.getString(2));
            }

            data = JDBC.getInstance().getPreparedStatement("SELECT * FROM carlook.endkunde WHERE username_fk = ?");
            data.setString(1, getPKFromUser(user.getUsername()));
            result = data.executeQuery();

            while(result.next()){
                kunde.setName(result.getString(2));
                kunde.setEndkundeID((result.getInt(1)));
            }

            return kunde;

        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
