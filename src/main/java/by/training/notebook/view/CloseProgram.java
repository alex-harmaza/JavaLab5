package by.training.notebook.view;

import by.training.notebook.bean.Request;
import by.training.notebook.view.exception.ViewException;

import java.util.Scanner;

/**
 * Created by alexh on 29.09.2016.
 */
public class CloseProgram extends View {

    @Override
    public Request createRequest(Scanner scanner) throws ViewException {
        System.exit(0);
        return null;
    }
}
