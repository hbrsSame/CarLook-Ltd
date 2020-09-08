package models.builder;

import entity.Reservierung;
import utils.Status;

public class ReservierungBuilder {
    Reservierung reservierung = new Reservierung();


    public ReservierungBuilder() {
        reservierung = new Reservierung();
        reservierung.setStatus(Status.BOOKED);
    }

    public ReservierungBuilder mitMarke(String marke){
        this.reservierung.setMarke(marke);
        return this;
    }

    public ReservierungBuilder mitDauer(int dauer){
        this.reservierung.setDauer(dauer);
        return this;
    }

    public ReservierungBuilder mitVertriebler_name(String name){
        this.reservierung.setVertriebler_name(name);
        return this;
    }

    public ReservierungBuilder mitEndkunden_name(String name){
        this.reservierung.setEndkunden_name(name);
        return this;
    }

    public ReservierungBuilder mitEndkunden_id(int id){
        this.reservierung.setEndkunden_id(id);
        return this;
    }

    public ReservierungBuilder mitAuto_id(int id){
        this.reservierung.setAuto_id(id);
        return this;
    }

    public ReservierungBuilder mitReservierung_id(int id){
        this.reservierung.setReservierung_id(id);
        return this;
    }

    public Reservierung createReservierung(){
        return this.reservierung;
    }
}
