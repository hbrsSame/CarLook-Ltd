package dao;

import database.JDBC;
import exceptions.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    private static LoginDAO instance;

    private LoginDAO() {}

    public static LoginDAO getInstance(){
        if(instance == null){
            return new LoginDAO();
        }
        return instance;
    }

    public boolean loginCheck(String username){

        try {
            PreparedStatement statementStudent = JDBC.getInstance().getPreparedStatement("SELECT username, password FROM carlook.test_tabelle WHERE username = ?");
            try {
                statementStudent.setString(1, username);

                ResultSet result = statementStudent.executeQuery();

                if(result.next()){
                    return true;
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return false;

    }
}
