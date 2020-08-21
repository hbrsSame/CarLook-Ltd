package exceptions;

public class DatabaseException extends Exception{
    private String messages;

    public DatabaseException ( String messages ) {
        this.messages = messages;

    }

    public String getMessages() {
        return messages;
    }

}
