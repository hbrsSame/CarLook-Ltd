package entity;

public class Vertriebler extends User {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public int getVertrieblerID() {
        return VertrieblerID;
    }

    public void setVertrieblerID(int vertrieblerID) {
        VertrieblerID = vertrieblerID;
    }

    private int VertrieblerID;
}
