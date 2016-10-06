package by.training.notebook.bean;

import by.training.notebook.CommandEnum;

/**
 * Created by Aliaksandr_Harmaza on 10/5/2016.
 */
public class Request {

    private CommandEnum command;


    public Request(CommandEnum command){
        setCommand(command);
    }


    public CommandEnum getCommand() {
        return command;
    }

    public void setCommand(CommandEnum command) {
        if (command == null){
            throw new NullPointerException("Command is null");
        }
        this.command = command;
    }
}
