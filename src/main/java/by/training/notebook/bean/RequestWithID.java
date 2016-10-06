package by.training.notebook.bean;

import by.training.notebook.CommandEnum;

/**
 * Created by alexh on 05.10.2016.
 */
public class RequestWithID extends Request {

    private Long id;


    public RequestWithID(CommandEnum command, Long id){
        super(command);
        setId(id);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
