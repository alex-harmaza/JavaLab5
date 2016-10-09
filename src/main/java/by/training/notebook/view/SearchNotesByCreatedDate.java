package by.training.notebook.view;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.*;
import by.training.notebook.bean.entity.ShortNote;
import by.training.notebook.view.exception.ViewException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Created by Aliaksandr_Harmaza on 9/28/2016.
 */
public class SearchNotesByCreatedDate extends View {

    @Override
    public Request createRequest(Scanner scanner) throws ViewException {
        Request request;
        try {
            DateFormat format = new SimpleDateFormat("d.M.y");
            System.out.print("\tEnter the date [day.month.year]: ");
            request = new RequestWithDate(CommandEnum.SEARCH_BY_CREATED_DATE,
                    format.parse(scanner.nextLine()));
        }
        catch (ParseException ex){
            throw new ViewException("Incorrect date", ex.getCause());
        }
        return request;
    }

    @Override
    public void showResponse(Response response) throws ViewException {
        if (response.getClass() != ResponseWithMessage.class
                && response.getClass() != ResponseWithNoteList.class){
            throw new ViewException("Incorrect response type");
        }

        if (response.getClass() == ResponseWithMessage.class){
            super.showResponse(response);
        }
        else {
            ResponseWithNoteList temp = (ResponseWithNoteList) response;
            System.out.println("Result: " + (temp.getNoteList().length == 0 ? "nothing" : "\n"));
            for (ShortNote note : temp.getNoteList()){
                System.out.println(note.toString() + "\n");
            }
        }
    }
}
