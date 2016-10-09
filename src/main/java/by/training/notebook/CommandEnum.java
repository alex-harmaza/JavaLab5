package by.training.notebook;

/**
 * Created by Aliaksandr_Harmaza on 10/5/2016.
 */
public enum CommandEnum {

    SHOW_NOTES("SN", "SHOW_NOTES (SN) - show records of the current user"),
    ADD_NOTE("AN", "ADD_NOTE (AN) - add note to the current user"),
    DELETE_NOTE("DN", "DELETE_NOTE (DN) - delete note by id"),
    DELETE_ALL_NOTES("DAN", "DELETE_ALL_NOTES (DAL) - delete all the notes of the current user"),
    SEARCH_BY_CREATED_DATE("SBCD", "SEARCH_BY_CREATED_DATE (SBCD) - search notes by content"),
    SEARCH_BY_CONTENT("SBC", "SEARCH_BY_CONTENT (SBC) - search notes by created date"),

    LOGIN_USER("LGN" , "LOGIN (LGN)"),
    REGISTER_USER("RGSTR", "REGISTER_USER (RGSTR)"),
    DELETE_USER("DU", "DELETE_USER (DU) - delete the current user"),

    HELP("H", "HELP (H) - information command"),
    EXIT("E", "EXIT (E) - exit program");


    public static CommandEnum getEnum(String name){
        CommandEnum result = getReducedCommand(name);
        return (result != null) ? result : CommandEnum.valueOf(name);
    }

    private static CommandEnum getReducedCommand(String name){
        CommandEnum commandEnum = null;
        for (CommandEnum e : CommandEnum.values()){
            if (e.getReducedCommand().equals(name)){
                commandEnum = e;
                break;
            }
        }
        return commandEnum;
    }


    private String reducedCommand;
    private String description;


    CommandEnum(String reducedCommand, String description){
        this.reducedCommand = reducedCommand;
        this.description = description;
    }


    public String getReducedCommand(){
        return this.reducedCommand;
    }

    public String getDescription(){
        return this.description;
    }
}
