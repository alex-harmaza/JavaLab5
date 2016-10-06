package by.training.notebook.bean.entity;

import java.util.Date;

/**
 * Created by Aliaksandr_Harmaza on 10/5/2016.
 */
public class Note {

    private Long id;
    private Date creationDate;
    private String message;
    private Long userID;


    public Note(){}

    public Note(Date createdDate, String message, Long userID){
        setCreationDate(createdDate);
        setMessage(message);
        setUserID(userID);
    }

    public Note(Long id, Date creationDate, String message, Long userID){
        setId(id);
        setCreationDate(creationDate);
        setMessage(message);
        setUserID(userID);
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
        this.creationDate = creationDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message.trim();
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
