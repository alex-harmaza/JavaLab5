package by.training.notebook.view;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.*;
import by.training.notebook.bean.entity.ShortNote;
import by.training.notebook.view.exception.ViewException;

import java.util.Scanner;

/**
 * Created by Aliaksandr_Harmaza on 9/28/2016.
 */
public class SearchNotesByContent extends View {

    @Override
    public Request createRequest(Scanner scanner) throws ViewException {
        Request request;
        try {
            System.out.print("\tEnter the content: ");
            request = new RequestWithContent(CommandEnum.SEARCH_BY_CONTENT,
                    scanner.nextLine());
        }
        catch (IllegalArgumentException ex){
            throw new ViewException(ex.getMessage(), ex);
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
            if (temp.getNoteList().length == 0){
                System.out.println("Result: Nothing");
            }
            else {
                System.out.println("Result:\n");
                for (ShortNote note : temp.getNoteList()){
                    System.out.println(note.toString() + "\n");
                }
            }
        }
    }

}
