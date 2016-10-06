package by.training.notebook.bean;

import by.training.notebook.CommandEnum;

/**
 * Created by alexh on 05.10.2016.
 */
public class RequestWithID extends Request {

    private long id;


    public RequestWithID(CommandEnum command, long id){
        super(command);
        setId(id);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
