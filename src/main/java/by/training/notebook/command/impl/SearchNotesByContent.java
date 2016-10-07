package by.training.notebook.command.impl;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.RequestWithContent;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
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
public class SearchNotesByContent implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {
        if (request == null || request.getCommand() != CommandEnum.SEARCH_NOTES_BY_CONTENT
                || request.getClass() != RequestWithContent.class){
            throw new CommandException("Incorrect request type");
        }

        RequestWithContent temp = (RequestWithContent) request;
        ShortNote[] result;

        try {
            result = ServiceFactory.getInstance().getNoteService()
                    .searchByContent(temp.getContent());
        }
        catch (ServiceException ex){
            throw new CommandException(ex);
        }

        return new ResponseWithNoteList(result);
    }

}
