package exceptions;

public class RegisterException extends Exception {

    private String messages;

    public RegisterException ( String messages ) {
        super(messages);
        this.messages = messages;

    }

}
