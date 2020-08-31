package control;


import dao.BookingDAO;
import entity.Reservierung;
import entity.User;
import exceptions.DatabaseException;

import java.util.List;

public class BookingControl {

    public static boolean bookCar(Reservierung reservierung)  throws DatabaseException{
        if(checkReservierung(reservierung)){
            return  BookingDAO.getInstance().bookCar(reservierung) != null;
        }else {
            throw new DatabaseException("Reservierungsdaten ungültig");
        }
    }

    private static boolean checkReservierung (Reservierung reservierung){
        return(reservierung.getAuto_id() != 0 && reservierung.getEndkunden_id() != 0
                && !reservierung.getEndkunden_name().isEmpty() && !reservierung.getVertriebler_name().isEmpty());
    }

    public static List<Reservierung> getBookingList(User user ){
        List<Reservierung> list = BookingDAO.getInstance().getList(user);
        if(list !=null) return list;
        else return null;
    }

    public static boolean cancelBooking(Reservierung reservierung) throws DatabaseException{
        if(checkReservierung(reservierung)){
            return BookingDAO.getInstance().cancelBooking(reservierung);
        }else{
            throw new DatabaseException("Reservierungdsdaten ungültig oder keine gültige Verbindung");
        }

    }
}
