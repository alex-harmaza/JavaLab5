package by.training.notebook.command.impl;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.MessageResponse;
import by.training.notebook.bean.TokenRequest;
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
                || request.getClass() != TokenRequest.class){
            throw new CommandException("Incorrect request");
        }

        TokenRequest temp = (TokenRequest) request;

        try {
            ServiceFactory.getInstance().getUserService()
                    .login(temp.getLogin(), temp.getPassword());
        } catch (ServiceException e) {
            throw new CommandException(e.getMessage(), e);
        }

        return new MessageResponse("You are logged");
    }

}
