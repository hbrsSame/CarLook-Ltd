package dao;

import database.JDBC;
import entity.Auto;
import entity.User;
import entity.Vertriebler;
import exceptions.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutoDAO {

    private static AutoDAO instance;

    private AutoDAO() {}

    public static AutoDAO getInstance(){
        if(instance == null){
            instance = new AutoDAO();
        }
        return instance;
    }

    public boolean createAuto(User user, Auto auto){
        PreparedStatement querry;
        try {
            if(user instanceof Vertriebler){
                querry = JDBC.getInstance().getPreparedStatement("INSERT INTO carlook.auto VALUES (default,?,?,?,?,?)");
                querry.setString(1,auto.getMarke());
                querry.setInt(2,Integer.parseInt(auto.getBaujahr()) );
                querry.setString(3, auto.getBeschreibung());
                querry.setInt(4, auto.getVertriebler_id());
                querry.setString(5, ((Vertriebler) user).getName());
                querry.execute();
            }
            JDBC.getInstance().closeConnection();

            return true;

        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Auto> getAllAutosFromDatabase(){
        List<Auto> autoList = new ArrayList<>();
        try {
            PreparedStatement querry = JDBC.getInstance().getPreparedStatement("SELECT * FROM carlook.auto");
            ResultSet data = querry.executeQuery();

            while(data.next()){
                Auto auto = new Auto();
                auto.setAuto_id(data.getInt(1));
                auto.setMarke(data.getString(2));
                auto.setBaujahr(data.getString(3));
                auto.setBeschreibung(data.getString(4));
                auto.setVertriebler_id(data.getInt(5));
                auto.setVertriebler_name(data.getString(6));

                autoList.add(auto);
            }
            JDBC.getInstance().closeConnection();
            return autoList;
        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
