package by.training.notebook.view;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.RequestWithID;
import by.training.notebook.view.exception.ViewException;

import java.util.Scanner;

/**
 * Created by alexh on 09.10.2016.
 */
public class DeleteNote extends View {

    @Override
    public Request createRequest(Scanner scanner) throws ViewException {
        Request request = null;
        try {
            System.out.print("\tEnter the note id: ");
            request = new RequestWithID(CommandEnum.DELETE_NOTE, Long.parseLong(scanner.nextLine().trim()));
        }
        catch (NumberFormatException ex){
            throw new ViewException("Incorrect value");
        }
        return request;
    }
}
