package entity;

public class Endkunde extends User {


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public int getEndkundeID() {
        return EndkundeID;
    }

    public void setEndkundeID(int endkundeID) {
        EndkundeID = endkundeID;
    }

    private int EndkundeID;

}
