package by.training.notebook.bean.entity;

import java.util.Date;

/**
 * Created by Aliaksandr_Harmaza on 10/7/2016.
 */
public class ShortNote {

    private Long id;
    private Date creationDate;
    private String content;


    public ShortNote(){}

    public ShortNote(Date createdDate, String content){
        setCreationDate(createdDate);
        setContent(content);
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


}
