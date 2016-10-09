package by.training.notebook.view;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.Request;
import by.training.notebook.view.exception.ViewException;

import java.util.Scanner;

/**
 * Created by alexh on 09.10.2016.
 */
public class DeleteCurrentUser extends View {

    @Override
    public Request createRequest(Scanner scanner) throws ViewException {
        return new Request(CommandEnum.DELETE_USER);
    }

}
