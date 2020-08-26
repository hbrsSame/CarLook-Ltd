package exceptions;

public class SessionException extends Exception{

    private String messages;

    public SessionException ( String messages ) {
        super(messages);
        this.messages = messages;

    }


}
