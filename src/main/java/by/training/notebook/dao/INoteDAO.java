package by.training.notebook.dao;

import by.training.notebook.bean.entity.Note;
import by.training.notebook.dao.exception.DAOException;
import by.training.notebook.dao.pool.ConnectionPool;

import java.util.Date;

/**
 * Created by alexh on 05.10.2016.
 */
public interface INoteDAO {

    void addNote(Note note, ConnectionPool.Connection connection) throws DAOException;
    void deleteNoteByID(long noteID, ConnectionPool.Connection connection) throws DAOException;
    Note[] getNoteList(ConnectionPool.Connection connection) throws DAOException;
    Note[] getNoteList(Date createdDate, ConnectionPool.Connection connection) throws DAOException;
    Note[] getNoteList(String content, ConnectionPool.Connection connection) throws DAOException;

}
