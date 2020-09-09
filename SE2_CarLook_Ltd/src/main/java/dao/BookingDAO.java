package dao;

import database.JDBC;
import entity.Endkunde;
import entity.Reservierung;
import entity.User;
import exceptions.DatabaseException;
import utils.SQL;
import utils.Status;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    private static BookingDAO instance;

    private BookingDAO() {}

    public static BookingDAO getInstance(){
        if(instance == null){
            instance = new BookingDAO();
        }
        return instance;
    }

    public Reservierung bookCar(Reservierung reservierung){

        try{
            PreparedStatement querry = JDBC.getInstance().getPreparedStatement(SQL.InsertIntoReservierung);
            querry.setInt(1,reservierung.getAuto_id());
            querry.setInt(2, reservierung.getEndkunden_id());
            querry.setString(3, reservierung.getEndkunden_name());
            querry.setString(4, reservierung.getVertriebler_name());
            querry.setInt(5, reservierung.getDauer());
            querry.setString(6, reservierung.getStatus());
            querry.setString(7, reservierung.getMarke());

            querry.execute();

            querry = JDBC.getInstance().getPreparedStatement(SQL.UpdateOnAutoStatus);
            querry.setString(1, Status.BOOKED);
            querry.setInt(2, reservierung.getAuto_id());

            querry.execute();

            return reservierung;

        }catch (DatabaseException | SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    public List<Reservierung> getList(User user){

        List<Reservierung> list = new ArrayList<>();

        try{
            PreparedStatement querry = JDBC.getInstance().getPreparedStatement(SQL.GetAllReservierungFromEndkunde);
            querry.setInt( 1, ((Endkunde) user).getEndkundeID() );

            ResultSet data = querry.executeQuery();

            while (data.next()) {
                Reservierung reservierung = new Reservierung();
                reservierung.setReservierung_id(data.getInt(1));
                reservierung.setAuto_id(data.getInt(2));
                reservierung.setEndkunden_id(data.getInt(3));
                reservierung.setEndkunden_name(data.getString(4));
                reservierung.setVertriebler_name(data.getString(5));
                reservierung.setDauer(data.getInt(6));
                reservierung.setStatus(data.getString(7));
                reservierung.setMarke(data.getString(8));

                list.add(reservierung);
            }

            return list;
        }catch ( DatabaseException | SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    public boolean cancelBooking(Reservierung reservierung){
        try{
            PreparedStatement querry = JDBC.getInstance().getPreparedStatement(SQL.UpdateOnAutoStatus);
            querry.setString(1, Status.FREE);
            querry.setInt(2, reservierung.getAuto_id());
            querry.execute();

            querry = JDBC.getInstance().getPreparedStatement(SQL.UpdateOnReservierungStatus);
            querry.setString(1, Status.CANCELED);
            querry.setInt(2, reservierung.getReservierung_id());
            querry.execute();

            return true;
        }catch ( DatabaseException | SQLException exception){
            exception.printStackTrace();
            return false;
        }


    }
}
