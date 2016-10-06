package by.training.notebook.service.impl;

import by.training.notebook.UserTypeEnum;
import by.training.notebook.bean.entity.Note;
import by.training.notebook.bean.entity.User;
import by.training.notebook.dao.exception.DAOException;
import by.training.notebook.dao.factory.DAOFactory;
import by.training.notebook.dao.pool.ConnectionPool;
import by.training.notebook.service.INoteBookService;
import by.training.notebook.service.exception.ServiceException;
import by.training.notebook.source.UserContext;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by alexh on 05.10.2016.
 */
public class NoteBookService implements INoteBookService {

    @Override
    public void addNote(String note) throws ServiceException {
        if (note == null || note.trim().isEmpty()){
            throw new ServiceException("Incorrect note");
        }
        if (UserContext.getInstance().getType() == UserTypeEnum.ANONYMOUS){
            throw new ServiceException("Unauthorized user");
        }

        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            ConnectionPool.Connection connection = pool.takeConnection();
            DAOFactory.getInstance().getNoteDAO().addNote(new Note(new Date(), note), connection);
            pool.returnConnection(connection);
        }
        catch (InterruptedException | SQLException | DAOException ex){
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteNote(long id) throws ServiceException {
        if (UserContext.getInstance().getType() == UserTypeEnum.ANONYMOUS){
            throw new ServiceException("Unauthorized user");
        }

        try {
            ConnectionPool.Connection connection = ConnectionPool.getInstance().takeConnection();
            DAOFactory.getInstance().getNoteDAO().deleteNoteByID(id, connection);
            ConnectionPool.getInstance().returnConnection(connection);
        } catch (InterruptedException | DAOException | SQLException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public Note[] searchByCreatedDate(Date createdDate) throws ServiceException {
        if (UserContext.getInstance().getType() == UserTypeEnum.ANONYMOUS){
            throw new ServiceException("Unauthorized user");
        }
        if (createdDate == null){
            throw new ServiceException("Incorrect date");
        }

        Note[] result;
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            ConnectionPool.Connection connection = pool.takeConnection();
            result = DAOFactory.getInstance().getNoteDAO().getNoteList(createdDate, connection);
            pool.returnConnection(connection);
        } catch (InterruptedException | DAOException | SQLException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }

        return result;
    }

    @Override
    public Note[] searchByNoteContent(String content) throws ServiceException {
        if (UserContext.getInstance().getType() == UserTypeEnum.ANONYMOUS){
            throw new ServiceException("Unauthorized user");
        }
        if (content == null || content.trim().isEmpty()){
            throw new ServiceException("Incorrect date");
        }

        Note[] result;
        try {
            ConnectionPool.Connection connection = ConnectionPool.getInstance().takeConnection();
            result = DAOFactory.getInstance().getNoteDAO().getNoteList(content, connection);
            ConnectionPool.getInstance().returnConnection(connection);
        } catch (InterruptedException | DAOException | SQLException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }

        return result;
    }

    @Override
    public boolean login(User user) throws ServiceException {
        if (user == null){
            throw new ServiceException("User is null");
        }

        boolean result;
        try {
            ConnectionPool.Connection connection = ConnectionPool.getInstance().takeConnection();
            result = DAOFactory.getInstance().getUserDAO().login(user, connection);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        catch (DAOException | SQLException |InterruptedException ex){
            throw new ServiceException(ex.getMessage(), ex);
        }

        return result;
    }

    @Override
    public void registerUser(User user) throws ServiceException {
        if (user == null){
            throw new ServiceException("User is null");
        }

        try {
            ConnectionPool.Connection connection = ConnectionPool.getInstance().takeConnection();
            DAOFactory.getInstance().getUserDAO().register(user, connection);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        catch (DAOException | SQLException |InterruptedException ex){
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteUser(User user) throws ServiceException {
        if (user == null){
            throw new ServiceException("User is null");
        }

        try {
            ConnectionPool.Connection connection = ConnectionPool.getInstance().takeConnection();
            DAOFactory.getInstance().getUserDAO().delete(user, connection);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        catch (DAOException | SQLException |InterruptedException ex){
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

}
