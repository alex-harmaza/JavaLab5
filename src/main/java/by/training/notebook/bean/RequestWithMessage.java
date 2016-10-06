package by.training.notebook.bean;

import by.training.notebook.CommandEnum;

/**
 * Created by Aliaksandr_Harmaza on 10/5/2016.
 */
public class RequestWithMessage extends Request {

    private String message;


    public RequestWithMessage(CommandEnum command, String message){
        super(command);
        setMessage(message);
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if (message == null){
            throw new NullPointerException("Message is null");
        }
        if (message.trim().isEmpty()){
            throw new IllegalArgumentException("Message is empty");
        }
        this.message = message;
    }
}
