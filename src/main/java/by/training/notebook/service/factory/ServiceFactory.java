package by.training.notebook.service.factory;

import by.training.notebook.service.INoteBookService;
import by.training.notebook.service.exception.ServiceException;
import by.training.notebook.service.impl.NoteBookService;

/**
 * Created by alexh on 05.10.2016.
 */
public class ServiceFactory {

    private final static ServiceFactory instance = new ServiceFactory();

    private final INoteBookService noteBookService = new NoteBookService();


    public static ServiceFactory getInstance(){
        return instance;
    }


    public INoteBookService getNoteBookService(){
        return noteBookService;
    }
}
