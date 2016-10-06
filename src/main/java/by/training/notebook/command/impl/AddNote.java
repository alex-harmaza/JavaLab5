package by.training.notebook.command.impl;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.RequestWithMessage;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.ResponseWithMessage;
import by.training.notebook.command.ICommand;
import by.training.notebook.command.exception.CommandException;
import by.training.notebook.service.exception.ServiceException;
import by.training.notebook.service.factory.ServiceFactory;

/**
 * Created by Aliaksandr_Harmaza on 10/5/2016.
 */
public class AddNote implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {
        if (request == null || request.getCommand() != CommandEnum.ADD_NOTE
                || request.getClass() != RequestWithMessage.class){
            throw new CommandException("Incorrect Request type");
        }

        RequestWithMessage requestWithMessage = (RequestWithMessage) request;
        try {
            ServiceFactory.getInstance().getNoteBookService().addNote(requestWithMessage.getMessage());
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage(), e);
        }

        return new ResponseWithMessage(true, "Result: note added");
    }

}
