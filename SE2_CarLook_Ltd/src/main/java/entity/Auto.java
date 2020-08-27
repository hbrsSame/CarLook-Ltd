package entity;

public class Auto {

    private String Marke;
    private String baujahr;
    private int auto_id;
    private int vertriebler_id;

    public String getVertriebler_name() {
        return vertriebler_name;
    }

    public void setVertriebler_name(String vertriebler_name) {
        this.vertriebler_name = vertriebler_name;
    }

    private String vertriebler_name;

    public int getAuto_id() {
        return auto_id;
    }

    public void setAuto_id(int auto_id) {
        this.auto_id = auto_id;
    }

    public int getVertriebler_id() {
        return vertriebler_id;
    }

    public void setVertriebler_id(int vertriebler_id) {
        this.vertriebler_id = vertriebler_id;
    }

    public String getMarke() {
        return Marke;
    }

    public void setMarke(String marke) {
        Marke = marke;
    }


    public String getBaujahr() {
        return baujahr;
    }

    public void setBaujahr(String baujahr) {
        this.baujahr = baujahr;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    private String beschreibung;
}
