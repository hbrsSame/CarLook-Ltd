package utils;

public class SQL {

    // INSERT - EINFÜGEN IN TABELLEN
    public final static String InsertIntoUser = "INSERT INTO carlook.user VALUES (?,?)";
    public final static String InsertIntoVertriebler = "INSERT INTO carlook.vertriebler VALUES (default,?,?)";
    public final static String InsertIntoEndkunde = "INSERT INTO carlook.endkunde VALUES (default,?,?)";
    public final static String InsertIntoAuto = "INSERT INTO carlook.auto VALUES (default,?,?,?,?,?)";
    public final static String InsertIntoReservierung = "INSERT INTO carlook.reservierung VALUES (default,?,?,?,?,?,?,?)";


    // SELECT - ABFRAGE VON DATEN AUS TABELLEN

    public final static String GetDataFromVertriebler = "SELECT * FROM carlook.vertriebler WHERE username_fk = ?";
    public final static String GetDataFromUser = "SELECT * FROM carlook.user WHERE username = ?";
    public final static String GetDataFromEndkunde = "SELECT * FROM carlook.endkunde WHERE username_fk = ?";
    public final static String GetAllAutosFromDatabase = "SELECT * FROM carlook.auto";
    public final static String GetAllAutosFromVertriebler = "SELECT * FROM carlook.auto WHERE vertriebler_id = ?";
    public final static String GetAllReservierungFromEndkunde = "SELECT * FROM carlook.reservierung WHERE endkunden_id = ? ";
    public final static String GetUsernameFromUser = "SELECT username FROM carlook.user WHERE username = ?";

    public final static String getUsernameFromUser = "SELECT username FROM carlook.user WHERE username = ?";

    // PK - PRIMARY KEYS VON TABELLEN
    public final static String getVertrieblerID = "SELECT vertriebler_id FROM carlook.vertriebler WHERE username_fk = ?";
    public final static String getEndkundeID = "SELECT endkunde_id FROM carlook.endkunde WHERE username_fk = ?";

    // DELETE - DATEN VON TABELLEN LÖSCHEN
    public final static String deleteDataFromAutoWithVertriebler = "DELETE FROM carlook.auto WHERE vertriebler_id = ?";
    public final static String deleteDataFromVetriebler = "DELETE FROM carlook.vertriebler WHERE username_fk = ?";
    public final static String deleteDataFromReservierungWithEndkunde = "DELETE FROM carlook.reservierung WHERE endkunden_id = ?";
    public final static String deleteDataFromEndkunde = "DELETE FROM carlook.endkunde WHERE username_fk = ?";
    public final static String deleteDataFromUser = "DELETE FROM carlook.user WHERE username= ?";

    //UPDATE - ÄNDERUNG VON DATEN IN TABELLEN
    public final static String UpdateOnAutoStatus = "UPDATE carlook.auto SET status = ? WHERE auto_id = ?";
    public final static String UpdateOnReservierungStatus = "UPDATE carlook.reservierung SET status = ? WHERE reservierung_id = ?";

}
