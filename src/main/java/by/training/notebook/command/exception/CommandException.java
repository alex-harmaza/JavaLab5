package by.training.notebook.command.exception;

/**
 * Created by Aliaksandr_Harmaza on 10/5/2016.
 */
public class CommandException extends Exception {

    public CommandException(String message){
        super(message);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }
}
