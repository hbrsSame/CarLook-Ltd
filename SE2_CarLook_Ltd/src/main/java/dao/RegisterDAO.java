package dao;

import database.JDBC;
import entity.Endkunde;
import entity.User;
import entity.Vertriebler;
import exceptions.DatabaseException;
import exceptions.NoUserFoundException;
import utils.SQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterDAO {

    private static RegisterDAO instance = null;

    private RegisterDAO () {}

    public static RegisterDAO getInstance(){
        if(instance == null){
            instance = new RegisterDAO();
        }
        return instance;
    }


    public boolean registerUser(User user) throws NoUserFoundException{
        boolean value = false;
        try {

            PreparedStatement querry = JDBC.getInstance().getPreparedStatement(SQL.InsertIntoUser);
            querry.setString(1, user.getUsername());
            querry.setString(2, user.getPassword());
            querry.execute();

            if(user instanceof Endkunde){
                querry = JDBC.getInstance().getPreparedStatement(SQL.InsertIntoEndkunde);
                querry.setString(1, ((Endkunde) user).getName());
                querry.setString(2, user.getUsername());
                querry.execute();

                value = true;

            }else if(user instanceof Vertriebler){
                querry = JDBC.getInstance().getPreparedStatement(SQL.InsertIntoVertriebler);
                querry.setString(1, ((Vertriebler) user).getName());
                querry.setString(2, user.getUsername());
                querry.execute();

                value = true;
            }
            else{
                value = false;
                throw new NoUserFoundException("User nicht gefunden oder ist kein gültiger Typ");
            }
        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }

        return value;
    }

    public boolean checkIfUserAlreadyExist(User user){
        PreparedStatement querry = null;
        try {
            querry = JDBC.getInstance().getPreparedStatement("SELECT username FROM carlook.user WHERE username = ?");
            querry.setString(1, user.getUsername());

            ResultSet data = querry.executeQuery();
            while(data.next()) {
                return (data.getString(1).equals(user.getUsername()));
            }
        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }
     return false;
    }







}
