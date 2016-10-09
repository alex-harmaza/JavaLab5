package by.training.notebook.dao;

import by.training.notebook.bean.entity.Note;
import by.training.notebook.bean.entity.ShortNote;
import by.training.notebook.dao.exception.DAOException;
import by.training.notebook.dao.pool.ConnectionPool;

import java.util.Date;

/**
 * Created by Aliaksandr_Harmaza on 10/6/2016.
 */
public interface INoteDAO {

    ShortNote[] getNoteList(long userID, ConnectionPool.Connection c) throws DAOException;
    void saveNote(Note note, ConnectionPool.Connection c) throws DAOException;
    void deleteNote(long noteID, long userID, ConnectionPool.Connection c) throws DAOException;
    void deleteNote(long userID, ConnectionPool.Connection c) throws DAOException;
    ShortNote[] searchByCreatedDate(Date createdDate, long userID, ConnectionPool.Connection c) throws DAOException;
    ShortNote[] searchByContent(String content, long userID, ConnectionPool.Connection c) throws DAOException;

}
