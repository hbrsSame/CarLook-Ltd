package exceptions;

public class UserAlreadyExistException extends Exception{

    private String messages;

    public UserAlreadyExistException(String messages){
        super(messages);
        this.messages = messages;
    }
}
