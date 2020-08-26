package exceptions;

public class DatabaseException extends Exception{
    private String messages;

    public DatabaseException ( String messages ) {
        super(messages);
        this.messages = messages;

    }


}
