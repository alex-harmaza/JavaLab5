package by.training.notebook.command.impl;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.ResponseWithNoteList;
import by.training.notebook.bean.entity.ShortNote;
import by.training.notebook.command.ICommand;
import by.training.notebook.command.exception.CommandException;
import by.training.notebook.service.exception.ServiceException;
import by.training.notebook.service.factory.ServiceFactory;

/**
 * Created by alexh on 09.10.2016.
 */
public class ShowNotes implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {

        if (request == null || request.getCommand() != CommandEnum.SHOW_NOTES
                || request.getClass() != Request.class){
            throw new CommandException("Incorrect request type");
        }

        ShortNote[] notes;
        try {
            notes = ServiceFactory.getInstance().getNoteService().showNotesByUser();
        }
        catch (ServiceException ex){
            throw new CommandException(ex.getMessage(), ex);
        }

        return new ResponseWithNoteList(notes);
    }

}
