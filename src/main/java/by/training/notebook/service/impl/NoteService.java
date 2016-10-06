package by.training.notebook.service.impl;

import by.training.notebook.service.impl.context.UserTypeEnum;
import by.training.notebook.bean.entity.Note;
import by.training.notebook.dao.exception.DAOException;
import by.training.notebook.dao.factory.DAOFactory;
import by.training.notebook.dao.pool.ConnectionPool;
import by.training.notebook.service.INoteService;
import by.training.notebook.service.exception.ServiceException;
import by.training.notebook.service.impl.context.UserContext;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by alexh on 05.10.2016.
 */
public class NoteService implements INoteService {

    @Override
    public void addNote(String note) throws ServiceException {
        if (note == null || note.trim().isEmpty()){
            throw new ServiceException("Incorrect note");
        }
        if (UserContext.getInstance().getType() == UserTypeEnum.ANONYMOUS){
            throw new ServiceException("Unauthorized user");
        }

        try {
            ConnectionPool.Connection connection = ConnectionPool.getInstance().takeConnection();
            Note temp = new Note(new Date(), note, UserContext.getInstance().getId());
            DAOFactory.getInstance().getNoteDAO().createNote(temp, connection);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        catch (InterruptedException | DAOException | SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteNoteByID(long id) throws ServiceException {
        if (UserContext.getInstance().getType() == UserTypeEnum.ANONYMOUS){
            throw new ServiceException("Unauthorized user");
        }

        try {
            ConnectionPool.Connection connection = ConnectionPool.getInstance().takeConnection();
            DAOFactory.getInstance().getNoteDAO()
                    .deleteNoteByIDAndUserID(id, UserContext.getInstance().getId(), connection);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        catch (InterruptedException | DAOException | SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteAllNotes() throws ServiceException {
        if (UserContext.getInstance().getType() == UserTypeEnum.ANONYMOUS){
            throw new ServiceException("Unauthorized user");
        }
        try {
            ConnectionPool.Connection connection = ConnectionPool.getInstance().takeConnection();
            DAOFactory.getInstance().getNoteDAO()
                    .deleteAllNotes(UserContext.getInstance().getId(), connection);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        catch (InterruptedException | DAOException | SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Note[] searchByCreatedDate(Date createdDate) throws ServiceException {
        if (createdDate == null){
            throw new ServiceException("Incorrect created date");
        }
        if (UserContext.getInstance().getType() == UserTypeEnum.ANONYMOUS){
            throw new ServiceException("Unauthorized user");
        }

        Note[] result;

        try {
            ConnectionPool.Connection connection = ConnectionPool.getInstance().takeConnection();
            result = DAOFactory.getInstance().getNoteDAO()
                    .searchByCreatedDate(createdDate, UserContext.getInstance().getId(), connection);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        catch (InterruptedException | DAOException | SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return result;
    }

    @Override
    public Note[] searchByContent(String content) throws ServiceException {
        if (content == null || content.trim().isEmpty()){
            throw new ServiceException("Incorrect content");
        }
        if (UserContext.getInstance().getType() == UserTypeEnum.ANONYMOUS){
            throw new ServiceException("Unauthorized user");
        }

        Note[] result;

        try {
            ConnectionPool.Connection connection = ConnectionPool.getInstance().takeConnection();
            result = DAOFactory.getInstance().getNoteDAO()
                    .searchByContent(content, UserContext.getInstance().getId(), connection);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        catch (InterruptedException | DAOException | SQLException e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return result;
    }
}
