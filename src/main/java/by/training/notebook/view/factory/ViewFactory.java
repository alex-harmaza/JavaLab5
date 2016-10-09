package by.training.notebook.view.factory;

import by.training.notebook.CommandEnum;
import by.training.notebook.view.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aliaksandr_Harmaza on 9/28/2016.
 */
public class ViewFactory {

    private Map<CommandEnum, View> viewMap;


    public ViewFactory() {
        this.viewMap = new HashMap<>();

        viewMap.put(CommandEnum.LOGIN_USER, new LoginUser());
        viewMap.put(CommandEnum.REGISTER_USER, new RegisterUser());
        viewMap.put(CommandEnum.DELETE_USER, new DeleteCurrentUser());

        viewMap.put(CommandEnum.SHOW_NOTES, new ShowNotes());
        viewMap.put(CommandEnum.ADD_NOTE, new AddNote());
        viewMap.put(CommandEnum.DELETE_NOTE, new DeleteNote());
        viewMap.put(CommandEnum.DELETE_ALL_NOTES, new DeleteAllNotes());
        viewMap.put(CommandEnum.SEARCH_NOTES_BY_CONTENT, new SearchNotesByContent());
        viewMap.put(CommandEnum.SEARCH_NOTES_BY_CREATED_DATE, new SearchNotesByCreatedDate());

        viewMap.put(CommandEnum.HELP, new ShowHelp());
        viewMap.put(CommandEnum.EXIT, new CloseProgram());
    }


    public View getView(CommandEnum commandEnum){
        return viewMap.get(commandEnum);
    }
}
