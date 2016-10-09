package by.training.notebook.dao.exception;

/**
 * Created by Aliaksandr_Harmaza on 10/5/2016.
 */
public class DAOException extends Exception {

    public DAOException(String message){
        super(message);
    }

    public DAOException(String message, Throwable cause){
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
