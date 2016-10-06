package by.training.notebook.dao.factory;

import by.training.notebook.dao.INoteDAO;
import by.training.notebook.dao.IUserDAO;
import by.training.notebook.dao.mysql.NoteDAO;
import by.training.notebook.dao.mysql.UserDAO;

/**
 * Created by alexh on 05.10.2016.
 */
public class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private final INoteDAO noteDAO = new NoteDAO();
    private final IUserDAO userDAO = new UserDAO();


    public static DAOFactory getInstance(){
        return instance;
    }


    public INoteDAO getNoteDAO(){
        return noteDAO;
    }

    public IUserDAO getUserDAO(){
        return userDAO;
    }
}
