package by.training.notebook.bean;

import by.training.notebook.CommandEnum;

/**
 * Created by Aliaksandr_Harmaza on 10/5/2016.
 */
public class ContentRequest extends Request {

    private String content;


    public ContentRequest(CommandEnum command, String content){
        super(command);
        setContent(content);
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
