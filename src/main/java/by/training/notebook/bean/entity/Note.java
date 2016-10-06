package by.training.notebook.bean.entity;

import java.util.Date;

/**
 * Created by Aliaksandr_Harmaza on 10/5/2016.
 */
public class Note {

    private Long id;
    private Date creationDate;
    private String message;


    public Note(Date creationDate, String message){
        setCreationDate(creationDate);
        setMessage(message);
    }

    public Note(Long id, Date creationDate, String message){
        this(creationDate, message);
        setId(id);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        if (creationDate == null){
            throw new NullPointerException("Date of creation is null");
        }
        this.creationDate = creationDate;
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
        this.message = message.trim();
    }
}
