package exceptions;

public class NoUserFoundException extends Exception {

    private String messages;


    public NoUserFoundException(String messages){
        super(messages);
        this.messages = messages;
    }
}
