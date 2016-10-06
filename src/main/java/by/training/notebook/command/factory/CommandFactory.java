package by.training.notebook.command.factory;

import by.training.notebook.CommandEnum;
import by.training.notebook.command.ICommand;
import by.training.notebook.command.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aliaksandr_Harmaza on 10/6/2016.
 */
public class CommandFactory {

    private static CommandFactory instance = new CommandFactory();

    private Map<CommandEnum, ICommand> map;


    public static CommandFactory getInstance(){
        return instance;
    }


    private CommandFactory(){
        map = new HashMap<>();
        map.put(CommandEnum.LOGIN_USER, new LoginUser());
        map.put(CommandEnum.REGISTER_USER, new RegisterUser());
        map.put(CommandEnum.DELETE_USER, new DeleteCurrentUser());

        map.put(CommandEnum.ADD_NOTE, new AddNote());
        map.put(CommandEnum.DELETE_NOTE, new DeleteNote());
        map.put(CommandEnum.DELETE_ALL_NOTES, new DeleteAllNotes());
        map.put(CommandEnum.SEARCH_NOTES_BY_CREATED_DATE, new SearchNotesByCreatedDate());
        map.put(CommandEnum.SEARCH_NOTES_BY_CONTENT, new SearchNotesByContent());
    }


    public ICommand getCommand(CommandEnum commandEnum){
        return map.get(commandEnum);
    }
}
