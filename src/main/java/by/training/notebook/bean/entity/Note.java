package by.training.notebook.bean.entity;

import java.util.Date;

/**
 * Created by Aliaksandr_Harmaza on 10/5/2016.
 */
public class Note extends ShortNote {

    private Long userID;


    public Note(){}

    public Note(Date createdDate, String content, Long userID){
        super(createdDate, content);
        setUserID(userID);
    }

    
    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
