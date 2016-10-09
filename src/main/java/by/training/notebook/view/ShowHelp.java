package by.training.notebook.view;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.ResponseWithMessage;
import by.training.notebook.view.exception.ViewException;

import java.util.Scanner;

/**
 * Created by alexh on 29.09.2016.
 */
public class ShowHelp extends View {

    @Override
    public Request createRequest(Scanner scanner) throws ViewException {
        return new Request(CommandEnum.HELP);
    }

    public void showResponse(Response response) throws ViewException {
        if (response.getClass() != ResponseWithMessage.class){
            throw new ViewException("Incorrect response type");
        }
        if (!response.isStatus()){
            super.showResponse(response);
        }
        else {
            System.out.println("Result:\n" + ((ResponseWithMessage) response).getMessage());
        }
    }
}
