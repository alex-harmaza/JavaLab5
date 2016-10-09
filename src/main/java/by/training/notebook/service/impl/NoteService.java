package by.training.notebook.service.impl;

import by.training.notebook.bean.entity.Note;
import by.training.notebook.bean.entity.ShortNote;
import by.training.notebook.dao.exception.DAOException;
import by.training.notebook.dao.factory.DAOFactory;
import by.training.notebook.dao.pool.ConnectionPool;
import by.training.notebook.service.INoteService;
import by.training.notebook.service.exception.ServiceException;
import by.training.notebook.service.impl.context.UserContext;
import by.training.notebook.service.impl.context.UserTypeEnum;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by alexh on 05.10.2016.
 */
public class NoteService implements INoteService {

    private final UserContext userContext = UserContext.getInstance();


    @Override
    public ShortNote[] showNotesByUser() throws ServiceException {
        if (userContext.getType() == UserTypeEnum.ANONYMOUS){
            throw new ServiceException("Unauthorized user");
        }

        ShortNote[] result;
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            ConnectionPool.Connection c = pool.takeConnection();
            try {
                result = DAOFactory.getInstance().getNoteDAO()
                        .getNoteList(userContext.getId(), c);
            }
            finally {
                pool.returnConnection(c);
            }
        }
        catch (DAOException | InterruptedException | SQLException ex){
            throw new ServiceException(ex.getMessage(), ex);
        }

        return result;
    }

    @Override
    public void addNote(String note) throws ServiceException {
        if (userContext.getType() == UserTypeEnum.ANONYMOUS){
            throw new ServiceException("Unauthorized user");
        }
        if (note == null || note.trim().isEmpty()){
            throw new ServiceException("Incorrect note");
        }
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            ConnectionPool.Connection c = pool.takeConnection(false);
            try {
                Note temp = new Note(new Date(), note, userContext.getId());
                DAOFactory.getInstance().getNoteDAO().saveNote(temp, c);
            }
            finally {
                pool.returnConnection(c);
            }
        }
        catch (InterruptedException | DAOException | SQLException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteNoteByID(long id) throws ServiceException {
        if (userContext.getType() == UserTypeEnum.ANONYMOUS){
            throw new ServiceException("Unauthorized user");
        }
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            ConnectionPool.Connection c = pool.takeConnection(false);
            try {
                DAOFactory.getInstance().getNoteDAO()
                        .deleteNote(id, userContext.getId(), c);
            }
            finally {
                pool.returnConnection(c);
            }
        }
        catch (InterruptedException | DAOException | SQLException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteAllNotes() throws ServiceException {
        if (userContext.getType() == UserTypeEnum.ANONYMOUS){
            throw new ServiceException("Unauthorized user");
        }
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            ConnectionPool.Connection c = pool.takeConnection(false);
            try {
                DAOFactory.getInstance().getNoteDAO()
                        .deleteNote(UserContext.getInstance().getId(), c);
            }
            finally {
                pool.returnConnection(c);
            }
        }
        catch (InterruptedException | DAOException | SQLException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public ShortNote[] searchByCreatedDate(Date createdDate) throws ServiceException {
        if (userContext.getType() == UserTypeEnum.ANONYMOUS){
            throw new ServiceException("Unauthorized user");
        }
        if (createdDate == null){
            throw new ServiceException("Incorrect created date");
        }

        ShortNote[] result;
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            ConnectionPool.Connection c = pool.takeConnection();
            try {
                result = DAOFactory.getInstance().getNoteDAO()
                        .searchByCreatedDate(createdDate, userContext.getId(), c);
            }
            finally {
                pool.returnConnection(c);
            }
        }
        catch (InterruptedException | DAOException | SQLException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }

        return result;
    }

    @Override
    public ShortNote[] searchByContent(String content) throws ServiceException {
        if (userContext.getType() == UserTypeEnum.ANONYMOUS){
            throw new ServiceException("Unauthorized user");
        }
        if (content == null || content.trim().isEmpty()){
            throw new ServiceException("Incorrect content");
        }

        ShortNote[] result;
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            ConnectionPool.Connection c = pool.takeConnection();
            try {
                result = DAOFactory.getInstance().getNoteDAO()
                        .searchByContent(content, UserContext.getInstance().getId(), c);
            }
            finally {
                pool.returnConnection(c);
            }
        }
        catch (InterruptedException | DAOException | SQLException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }

        return result;
    }
}
