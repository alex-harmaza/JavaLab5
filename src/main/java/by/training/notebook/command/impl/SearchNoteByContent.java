package by.training.notebook.command.impl;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.ContentRequest;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.entity.Note;
import by.training.notebook.bean.NoteListResponse;
import by.training.notebook.command.ICommand;
import by.training.notebook.command.exception.CommandException;
import by.training.notebook.service.exception.ServiceException;
import by.training.notebook.service.factory.ServiceFactory;

/**
 * Created by Aliaksandr_Harmaza on 10/6/2016.
 */
public class SearchNoteByContent implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {
        if (request == null || request.getCommand() != CommandEnum.SEARCH_NOTES_BY_CONTENT
                || request.getClass() != ContentRequest.class){
            throw new CommandException("Incorrect request type");
        }

        ContentRequest temp = (ContentRequest) request;
        Note[] result;

        try {
            result = ServiceFactory.getInstance().getNoteService()
                    .searchByContent(temp.getContent());
        }
        catch (ServiceException ex){
            throw new CommandException(ex.getMessage(), ex);
        }

        return new NoteListResponse(result);
    }

}
