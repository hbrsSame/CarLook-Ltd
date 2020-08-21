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

public class KeyDAO {

    public static int getPKFromVertriebler (UserLoginDTO user){

        int pk = 0;
        PreparedStatement data = null;
        try {
            data = JDBC.getInstance().getPreparedStatement("SELECT vertriebler_id FROM carlook.vertriebler WHERE username_fk = ?");
            data.setString(1, getPKFromUser(user.getUsername()));

            ResultSet result = data.executeQuery();

            while( result.next()){
                pk = result.getInt(1);
            }

            return pk;

        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }

        return pk;
    }

    public static int getPKFromEndkunde (UserLoginDTO user){

        int pk = 0;
        PreparedStatement data = null;
        try {
            data = JDBC.getInstance().getPreparedStatement("SELECT endkunde_id FROM carlook.endkunde WHERE username_fk = ?");
            data.setString(1, getPKFromUser(user.getUsername()));

            ResultSet result = data.executeQuery();

            while( result.next()){
                pk = result.getInt(1);
            }

            return pk;

        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }

        return pk;
    }

    public static String getPKFromUser (String username){

        String pk = "";
        PreparedStatement data = null;
        try {
            data = JDBC.getInstance().getPreparedStatement("SELECT username FROM carlook.user WHERE username = ?");
            data.setString(1, username);

            ResultSet result = data.executeQuery();

            while( result.next()){
                pk = result.getString(1);
            }

            return pk;

        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }

        return pk;
    }


}
