package utils;

public class SQL {

    // INSERT - EINFÜGEN IN TABELLEN
    public final static String InsertIntoUser = "INSERT INTO carlook.user VALUES (?,?)";
    public final static String InsertIntoVertriebler = "INSERT INTO carlook.vertriebler VALUES (default,?,?)";
    public final static String InsertIntoEndkunde = "INSERT INTO carlook.endkunde VALUES (default,?,?)";

    // SELECT - ABFRAGE VON DATEN AUS TABELLEN

    public final static String GetDataFromVertriebler = "SELECT * FROM carlook.vertriebler WHERE username_fk = ?";
    public final static String GetDataFromUser = "SELECT * FROM carlook.user WHERE username = ?";
    public final static String GetDataFromEndkunde = "SELECT * FROM carlook.endkunde WHERE username_fk = ?";

    public final static String getUsernameFromUser = "SELECT username FROM carlook.user WHERE username = ?";

    // PK - PRIMARY KEYS VON TABELLEN
    public final static String getVertrieblerID = "SELECT vertriebler_id FROM carlook.vertriebler WHERE username_fk = ?";
    public final static String getEndkundeID = "SELECT endkunde_id FROM carlook.endkunde WHERE username_fk = ?";

    // DELETE - DATEN VON TABELLEN LÖSCHEN

    //UPDATE - ÄNDERUNG VON DATEN IN TABELLEN
}
