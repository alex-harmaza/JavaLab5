package by.training.notebook.command.impl;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.ResponseWithMessage;
import by.training.notebook.command.ICommand;
import by.training.notebook.command.exception.CommandException;
import by.training.notebook.service.factory.ServiceFactory;

/**
 * Created by alexh on 09.10.2016.
 */
public class ShowHelp implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {
        if (request == null || request.getClass() != Request.class
                || request.getCommand() != CommandEnum.HELP){
            throw new CommandException("Incorrect request type");
        }
        return new ResponseWithMessage(ServiceFactory.getInstance()
                .getCommandLineService().getHelpDescription());
    }
}
