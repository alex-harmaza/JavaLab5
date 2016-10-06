package by.training.notebook.command;

import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.command.exception.CommandException;

/**
 * Created by Aliaksandr_Harmaza on 10/5/2016.
 */
public interface ICommand {

    Response execute(Request request) throws CommandException;

}
