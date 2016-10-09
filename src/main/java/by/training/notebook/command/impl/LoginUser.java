package by.training.notebook.command.impl;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.RequestWithToken;
import by.training.notebook.bean.Response;
import by.training.notebook.command.ICommand;
import by.training.notebook.command.exception.CommandException;
import by.training.notebook.service.exception.ServiceException;
import by.training.notebook.service.factory.ServiceFactory;

/**
 * Created by Aliaksandr_Harmaza on 10/6/2016.
 */
public class LoginUser implements ICommand {

    @Override
    public Response execute(Request request) throws CommandException {
        if (request == null || request.getCommand() != CommandEnum.LOGIN_USER
                || request.getClass() != RequestWithToken.class){
            throw new CommandException("Incorrect request");
        }

        RequestWithToken temp = (RequestWithToken) request;
        boolean result;

        try {
            result = ServiceFactory.getInstance().getUserService()
                    .login(temp.getLogin(), temp.getPassword());
        } catch (ServiceException ex) {
            throw new CommandException(ex.getMessage(), ex);
        }

        return new Response(result);
    }

}
