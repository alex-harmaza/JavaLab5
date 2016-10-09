package by.training.notebook.view.exception;

/**
 * Created by Aliaksandr_Harmaza on 9/28/2016.
 */
public class ViewException extends Exception {

    public ViewException(String message){
        super(message);
    }

    public ViewException(String message, Throwable cause){
        super(message, cause);
    }

    public ViewException(Throwable cause){
        super(cause);
    }

}
