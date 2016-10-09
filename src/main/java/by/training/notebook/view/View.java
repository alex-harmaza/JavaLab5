package by.training.notebook.view;

import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.ResponseWithMessage;
import by.training.notebook.view.exception.ViewException;

import java.util.Scanner;

/**
 * Created by Aliaksandr_Harmaza on 9/28/2016.
 */
public abstract class View {

    public abstract Request createRequest(Scanner scanner) throws ViewException;

    public void showResponse(Response response) throws ViewException {
        if (response.getClass() != ResponseWithMessage.class){
            throw new ViewException("Incorrect response type");
        }
        System.out.println(((!response.isStatus()) ? "Error: " : "Result: ")
                + ((ResponseWithMessage) response).getMessage());
    }
}
