package by.training.notebook.bean;

import by.training.notebook.CommandEnum;

import java.util.Date;

/**
 * Created by Aliaksandr_Harmaza on 10/6/2016.
 */
public class DateRequest extends Request {

    private Date date;


    public DateRequest(CommandEnum command, Date date){
        super(command);
        setDate(date);
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
