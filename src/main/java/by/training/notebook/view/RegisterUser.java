package by.training.notebook.view;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.RequestWithToken;
import by.training.notebook.view.exception.ViewException;

import java.util.Scanner;

/**
 * Created by alexh on 09.10.2016.
 */
public class RegisterUser extends View {

    @Override
    public Request createRequest(Scanner scanner) throws ViewException {
        Request request;
        try {
            System.out.print("\tEnter the login: ");
            String login = scanner.nextLine();

            System.out.print("\tEnter the password: ");
            String password = scanner.nextLine();

            request = new RequestWithToken(CommandEnum.REGISTER_USER, login, password);
        }
        catch (IllegalArgumentException ex){
            throw new ViewException(ex.getMessage(), ex);
        }
        return request;
    }

}
