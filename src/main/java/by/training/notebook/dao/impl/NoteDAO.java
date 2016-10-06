package by.training.notebook.dao.impl;

import by.training.notebook.bean.entity.Note;
import by.training.notebook.dao.INoteDAO;
import by.training.notebook.dao.exception.DAOException;
import by.training.notebook.dao.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Aliaksandr_Harmaza on 10/6/2016.
 */
public class NoteDAO implements INoteDAO {

    private final static String ADD_NOTE_QUERY = "INSERT INTO Note (createdDate, message, userID) VALUES (?, ?, ?)";
    private final static String UPDATE_NOTE_QUERY = "UPDATE INTO Note SET createdDate = ? message = ? userID = ? WHERE id = ?";
    private final static String SEARCH_BY_CONTENT_QUERY = "SELECT * FROM Note WHERE message = ? and userID = ?";
    private final static String SEARCH_BY_CREATED_DATE_QUERY = "SELECT * FROM Note WHERE createdDate = ? and userID = ?";
    private final static String DELETE_BY_ID_AND_USER_ID_QUERY = "DELETE_USER FROM Note where id = ? AND userID = ?";
    private final static String DELETE_BY_USER_ID_QUERY = "DELETE FROM Note where userID = ?";


    @Override
    public void createNote(Note note, ConnectionPool.Connection connection) throws DAOException {
        try (PreparedStatement s = connection
                .prepareStatement((note.getId() == null) ? ADD_NOTE_QUERY : UPDATE_NOTE_QUERY)) {
            s.setLong(1, note.getCreationDate().getTime());
            s.setString(2, note.getMessage());
            s.setLong(3, note.getUserID());

            if (note.getId() != null){
                s.setLong(4, note.getId());
            }
            s.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteNoteByIDAndUserID(long noteID, long userID, ConnectionPool.Connection connection) throws DAOException {
        if (connection == null){
            throw new DAOException("Connection is null");
        }
        try (PreparedStatement s = connection.prepareStatement(DELETE_BY_ID_AND_USER_ID_QUERY)) {
            s.setLong(1, noteID);
            s.setLong(2, userID);
            s.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllNotes(long userID, ConnectionPool.Connection connection) throws DAOException {
        if (connection == null){
            throw new DAOException("Connection is null");
        }
        try (PreparedStatement s = connection.prepareStatement(DELETE_BY_USER_ID_QUERY)) {
            s.setLong(1, userID);
            s.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Note[] searchByCreatedDate(Date createdDate, long userID, ConnectionPool.Connection connection) throws DAOException {
        if (createdDate == null){
            throw new DAOException("Content is null");
        }
        if (connection == null){
            throw new DAOException("Connection is null");
        }

        List<Note> result = new ArrayList<>();
        try (PreparedStatement s = connection.prepareStatement(SEARCH_BY_CREATED_DATE_QUERY)) {
            s.setLong(1, createdDate.getTime());
            s.setLong(2, userID);
            ResultSet resultSet = s.executeQuery();

            while (resultSet.next()){
                Note note = new Note();
                note.setId(resultSet.getLong("id"));
                note.setCreationDate(new Date(resultSet.getLong("createdDate")));
                note.setMessage(resultSet.getString("message"));
                note.setUserID(resultSet.getLong("userID"));
                result.add(note);
            }

        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }

        return result.toArray(new Note[result.size()]);
    }

    @Override
    public Note[] searchByContent(String content, long userID, ConnectionPool.Connection connection) throws DAOException {
        if (content == null){
            throw new DAOException("Content is null");
        }
        if (connection == null){
            throw new DAOException("Connection is null");
        }

        List<Note> result = new ArrayList<>();
        try (PreparedStatement s = connection.prepareStatement(SEARCH_BY_CONTENT_QUERY)) {
            s.setString(1, content);
            s.setLong(2, userID);
            ResultSet resultSet = s.executeQuery();

            while (resultSet.next()){
                Note note = new Note();
                note.setId(resultSet.getLong("id"));
                note.setCreationDate(new Date(resultSet.getLong("createdDate")));
                note.setMessage(resultSet.getString("message"));
                note.setUserID(resultSet.getLong("userID"));
                result.add(note);
            }

        } catch (SQLException e) {
            throw new DAOException(e.getMessage(), e);
        }

        return result.toArray(new Note[result.size()]);
    }


}