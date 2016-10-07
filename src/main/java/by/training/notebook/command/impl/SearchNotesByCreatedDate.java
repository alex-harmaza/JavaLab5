package by.training.notebook.command.impl;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.RequestWithDate;
import by.training.notebook.bean.entity.Note;
import by.training.notebook.bean.ResponseWithNoteList;
import by.training.notebook.bean.entity.ShortNote;
import by.training.notebook.command.ICommand;
import by.training.notebook.command.exception.CommandException;
import by.training.notebook.service.exception.ServiceException;
import by.training.notebook.service.factory.ServiceFactory;

/**
 * Created by Aliaksandr_Harmaza on 10/6/2016.
 */
public class SearchNotesByCreatedDate implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {
        if (request == null || request.getCommand() != CommandEnum.SEARCH_NOTES_BY_CREATED_DATE
                || request.getClass() != RequestWithDate.class){
            throw new CommandException("Incorrect request type");
        }

        ShortNote[] result;
        RequestWithDate temp = (RequestWithDate) request;

        try {
            result = ServiceFactory.getInstance().getNoteService().searchByCreatedDate(temp.getDate());
        }
        catch (ServiceException ex){
            throw new CommandException(ex);
        }

        return new ResponseWithNoteList(result);
    }


}
