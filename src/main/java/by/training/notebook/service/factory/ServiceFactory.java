package by.training.notebook.service.factory;

import by.training.notebook.service.INoteService;
import by.training.notebook.service.IUserService;
import by.training.notebook.service.impl.NoteService;
import by.training.notebook.service.impl.UserService;

/**
 * Created by alexh on 05.10.2016.
 */
public class ServiceFactory {

    private final static ServiceFactory instance = new ServiceFactory();

    private final INoteService noteService = new NoteService();
    private final IUserService userService = new UserService();


    public static ServiceFactory getInstance(){
        return instance;
    }


    public INoteService getNoteService(){
        return noteService;
    }

    public IUserService getUserService(){
        return userService;
    }
}
