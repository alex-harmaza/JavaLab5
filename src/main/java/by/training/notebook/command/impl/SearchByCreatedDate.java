package by.training.notebook.command.impl;

import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.command.ICommand;
import by.training.notebook.command.exception.CommandException;

/**
 * Created by alexh on 05.10.2016.
 */
public class SearchByCreatedDate implements ICommand {
    @Override
    public Response execute(Request request) throws CommandException {
        return null;
    }
}
