package by.training.notebook.service.exception;

/**
 * Created by alexh on 05.10.2016.
 */
public class ServiceException extends Exception {

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message, Throwable cause){
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

}
