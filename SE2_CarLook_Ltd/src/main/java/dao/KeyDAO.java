package dao;

import database.JDBC;
import dto.UserLoginDTO;
import exceptions.DatabaseException;
import utils.SQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KeyDAO {

    public static int getPKFromVertriebler (UserLoginDTO user){

        int pk = 0;
        PreparedStatement data = null;
        try {
            data = JDBC.getInstance().getPreparedStatement(SQL.getVertrieblerID);
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
            data = JDBC.getInstance().getPreparedStatement(SQL.getEndkundeID);
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
            data = JDBC.getInstance().getPreparedStatement(SQL.getUsernameFromUser);
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
