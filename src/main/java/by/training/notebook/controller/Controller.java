package by.training.notebook.controller;

import by.training.notebook.bean.MessageResponse;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.command.ICommand;
import by.training.notebook.command.exception.CommandException;
import by.training.notebook.command.factory.CommandFactory;

/**
 * Created by Aliaksandr_Harmaza on 10/6/2016.
 */
public class Controller {

    public Response doRequest(Request request){
        Response response;

        try {
            ICommand command = CommandFactory.getInstance().getCommand(request.getCommand());
            response = command.execute(request);
        }
        catch (NullPointerException ex){
            response = new MessageResponse(false, "Incorrect command");
        }
        catch (CommandException ex){
            response = new MessageResponse(false, ex.getMessage());
        }

        return response;
    }
}
