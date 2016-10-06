package by.training.notebook.bean.entity;

import java.util.Date;

/**
 * Created by Aliaksandr_Harmaza on 10/5/2016.
 */
public class Note {

    private Long id;
    private Date creationDate;
    private String content;
    private Long userID;


    public Note(){}

    public Note(Date createdDate, String content, Long userID){
        setCreationDate(createdDate);
        setContent(content);
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content.trim();
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
