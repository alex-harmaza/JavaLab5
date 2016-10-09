package by.training.notebook.bean;

import by.training.notebook.CommandEnum;

/**
 * Created by Aliaksandr_Harmaza on 10/5/2016.
 */
public class RequestWithContent extends Request {

    private String content;


    public RequestWithContent(CommandEnum command, String content){
        super(command);
        setContent(content);
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content == null || content.trim().isEmpty()){
            throw new IllegalArgumentException("Incorrect content");
        }
        this.content = content.trim();
    }
}
