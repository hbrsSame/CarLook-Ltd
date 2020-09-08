package models.builder;

import entity.Auto;
import utils.Status;

public class AutoBuilder {

    private Auto auto;

    public AutoBuilder() {
        auto = new Auto();
        this.auto.setStatus(Status.FREE);
    }

    public AutoBuilder mitMarke(String marke){
        this.auto.setMarke(marke);
        return this;
    }

    public AutoBuilder mitBaujahr(String baujahr){
        this.auto.setBaujahr(baujahr);
        return this;
    }

    public AutoBuilder mitVertriebler_name(String name){
        this.auto.setVertriebler_name(name);
        return this;
    }

    public AutoBuilder mitVertriebler_id (int id){
        this.auto.setVertriebler_id(id);
        return this;
    }

    public AutoBuilder mitBeschreibung(String beschreibung){
        this.auto.setBeschreibung(beschreibung);
        return this;
    }

    public Auto createAuto(){
        return this.auto;
    }


}
