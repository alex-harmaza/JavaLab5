package by.training.notebook.command.impl;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.ContentRequest;
import by.training.notebook.bean.MessageResponse;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.command.ICommand;
import by.training.notebook.command.exception.CommandException;
import by.training.notebook.service.exception.ServiceException;
import by.training.notebook.service.factory.ServiceFactory;

/**
 * Created by Aliaksandr_Harmaza on 10/6/2016.
 */
public class AddNote implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {
        if (request == null || request.getCommand() != CommandEnum.ADD_NOTE
                || request.getClass() != ContentRequest.class){
            throw new CommandException("Incorrect request type");
        }

        ContentRequest temp = (ContentRequest) request;
        try {
            ServiceFactory.getInstance().getNoteService().addNote(temp.getContent());
        }
        catch (ServiceException ex){
            throw new CommandException(ex.getMessage(), ex);
        }

        return new MessageResponse("Note added");
    }

}
