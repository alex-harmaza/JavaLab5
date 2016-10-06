package by.training.notebook.bean;

/**
 * Created by Aliaksandr_Harmaza on 10/5/2016.
 */
public class MessageResponse extends Response {

    private String message;


    public MessageResponse(String message){
        super();
        setMessage(message);
    }

    public MessageResponse(boolean status, String message){
        super(status);
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
