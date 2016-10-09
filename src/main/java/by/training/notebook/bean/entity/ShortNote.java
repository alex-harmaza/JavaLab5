package by.training.notebook.bean.entity;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Aliaksandr_Harmaza on 10/7/2016.
 */
public class ShortNote {

    private Long id;
    private Date createdDate;
    private String content;


    public ShortNote(){}

    public ShortNote(Date createdDate, String content){
        setCreatedDate(createdDate);
        setContent(content);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content.trim();
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (id != null){
            builder.append("id: ").append(id).append("\n");
        }
        if (createdDate != null){
            builder.append("createdDate: ").append(DateFormat
                    .getDateTimeInstance().format(createdDate))
                    .append("\n");
        }
        if (content != null){
            builder.append("content: ").append(content);
        }
        return builder.toString();
    }
}
