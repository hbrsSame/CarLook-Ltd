package database;

import exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class JDBC {

    private static JDBC connection = null;

    private String url = "jdbc:postgresql://dumbo.inf.h-brs.de/";
    private Connection conn;

    private String login = "shasch2s";
    private String password = "shasch2s";

    public static JDBC getInstance() throws DatabaseException {
        if(connection == null){
            connection = new JDBC();
        }
        return connection;
    }


    private JDBC() throws DatabaseException {

        this.initConnection();
    }

    public void initConnection() throws DatabaseException{

        try {
            DriverManager.registerDriver( new org.postgresql.Driver() );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.openConnection();

    }

    public void openConnection() throws DatabaseException {

        Properties props = new Properties();
        props.setProperty("user", login);
        props.setProperty("password", password);

        try {
            this.conn = DriverManager.getConnection(this.url, props);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DatabaseException("Fehler beim Verbinden zum Server. Stelle sicher, dass Du Zugriff auf das VPN-Netzwerk hast!");
        }

    }

    public Connection getConnection(){

        try {
            if(this.conn.isClosed() ){
                this.openConnection();
            }
            return this.conn;
        } catch (SQLException | DatabaseException e) {
            e.printStackTrace();
            return  null;
        }
    }


    public PreparedStatement getPreparedStatement(String sql ){

        try {
            if(this.conn.isClosed() ) {
                this.openConnection();
            }
            return this.conn.prepareStatement(sql);

        } catch (SQLException | DatabaseException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }


    public void closeConnection(){
        try {
            this.conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
