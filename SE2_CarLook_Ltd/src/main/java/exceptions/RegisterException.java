package exceptions;

public class RegisterException extends Exception {

    private String messages;

    public RegisterException ( String messages ) {
        this.messages = messages;

    }

    public String getMessages() {
        return messages;
    }
}
