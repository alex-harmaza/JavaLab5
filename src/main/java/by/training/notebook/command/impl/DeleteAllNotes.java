package by.training.notebook.command.impl;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.ResponseWithMessage;
import by.training.notebook.command.ICommand;
import by.training.notebook.command.exception.CommandException;
import by.training.notebook.service.exception.ServiceException;
import by.training.notebook.service.factory.ServiceFactory;

/**
 * Created by Aliaksandr_Harmaza on 10/6/2016.
 */
public class DeleteAllNotes implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {
        if (request == null || request.getCommand() != CommandEnum.DELETE_ALL_NOTES
                || request.getClass() != Request.class){
            throw new CommandException("Incorrect request type");
        }

        try {
            ServiceFactory.getInstance().getNoteService().deleteAllNotes();
        }
        catch (ServiceException ex){
            throw new CommandException(ex.getMessage(), ex);
        }

        return new ResponseWithMessage("Notes deleted");
    }

}
