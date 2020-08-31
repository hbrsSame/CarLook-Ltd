package entity;

public class Reservierung {

    private int reservierung_id;
    private int auto_id;
    private int dauer;
    private String status;
    private String marke;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDauer() {
        return dauer;
    }

    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    public int getReservierung_id() {
        return reservierung_id;
    }

    public void setReservierung_id(int reservierung_id) {
        this.reservierung_id = reservierung_id;
    }

    public int getAuto_id() {
        return auto_id;
    }

    public void setAuto_id(int auto_id) {
        this.auto_id = auto_id;
    }

    public int getEndkunden_id() {
        return endkunden_id;
    }

    public void setEndkunden_id(int endkunden_id) {
        this.endkunden_id = endkunden_id;
    }

    public String getVertriebler_name() {
        return vertriebler_name;
    }

    public void setVertriebler_name(String vertriebler_name) {
        this.vertriebler_name = vertriebler_name;
    }

    public String getEndkunden_name() {
        return endkunden_name;
    }

    public void setEndkunden_name(String endkunden_name) {
        this.endkunden_name = endkunden_name;
    }

    private int endkunden_id;
    private String vertriebler_name;
    private String endkunden_name;


    public String getMarke() {
        return marke;
    }

    public void setMarke(String marke) {
        this.marke = marke;
    }
}
