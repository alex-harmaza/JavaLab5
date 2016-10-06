package by.training.notebook.command.impl;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.RequestWithID;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.ResponseWithMessage;
import by.training.notebook.command.ICommand;
import by.training.notebook.command.exception.CommandException;
import by.training.notebook.service.exception.ServiceException;
import by.training.notebook.service.factory.ServiceFactory;

/**
 * Created by alexh on 05.10.2016.
 */
public class DeleteNote implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {
        if (request == null || request.getClass() != RequestWithID.class
                || request.getCommand() != CommandEnum.DELETE_NOTE){
            throw new CommandException("Incorrect request type");
        }

        RequestWithID requestWithID = (RequestWithID) request;
        try {
            ServiceFactory.getInstance().getNoteBookService().deleteNote(requestWithID.getId());
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage(), e);
        }

        return new ResponseWithMessage("Note is deleted");
    }

}
