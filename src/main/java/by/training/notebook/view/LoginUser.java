package by.training.notebook.view;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.RequestWithToken;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.ResponseWithMessage;
import by.training.notebook.view.exception.ViewException;

import java.util.Scanner;

/**
 * Created by alexh on 09.10.2016.
 */
public class LoginUser extends View {

    @Override
    public Request createRequest(Scanner scanner) throws ViewException {
        Request request;
        try {
            System.out.print("\tEnter the login: ");
            String login = scanner.nextLine();

            System.out.print("\tEnter the password: ");
            String password = scanner.nextLine();

            request = new RequestWithToken(CommandEnum.LOGIN_USER, login, password);
        }
        catch (IllegalArgumentException ex){
            throw new ViewException(ex.getMessage(), ex);
        }
        return request;
    }


    @Override
    public void showResponse(Response response) throws ViewException {
        if (response.getClass() == ResponseWithMessage.class){
            super.showResponse(response);
        }
        if (response.getClass() != Response.class){
            throw new ViewException("Incorrect response");
        }
        System.out.println(response.isStatus() ? "Result: you are logged" : "Result: you are not logged");
    }
}
