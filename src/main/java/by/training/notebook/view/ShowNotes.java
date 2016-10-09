package by.training.notebook.view;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.Request;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.ResponseWithMessage;
import by.training.notebook.bean.ResponseWithNoteList;
import by.training.notebook.bean.entity.ShortNote;
import by.training.notebook.view.exception.ViewException;

import java.util.Scanner;

/**
 * Created by Aliaksandr_Harmaza on 9/28/2016.
 */
public class ShowNotes extends View {

    @Override
    public Request createRequest(Scanner scanner) throws ViewException {
        return new Request(CommandEnum.SHOW_NOTES);
    }

    @Override
    public void showResponse(Response response) throws ViewException {
        if (response.getClass() != ResponseWithNoteList.class
                && response.getClass() != ResponseWithMessage.class){
            throw new ViewException("Incorrect response type");
        }

        if (response.getClass() == ResponseWithMessage.class){
            super.showResponse(response);
        }
        else {
            ResponseWithNoteList temp = (ResponseWithNoteList) response;
            if (temp.getNoteList().length == 0){
                System.out.println("Result: nothing");
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
