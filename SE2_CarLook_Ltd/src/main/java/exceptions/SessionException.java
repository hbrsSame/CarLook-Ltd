package exceptions;

public class SessionException extends Exception{

    private String messages;

    public SessionException ( String messages ) {
        this.messages = messages;

    }

    public String getMessages() {
        return messages;
    }
}
