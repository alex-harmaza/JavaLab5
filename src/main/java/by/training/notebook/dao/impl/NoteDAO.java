package by.training.notebook.dao.impl;

import by.training.notebook.bean.entity.Note;
import by.training.notebook.bean.entity.ShortNote;
import by.training.notebook.dao.INoteDAO;
import by.training.notebook.dao.exception.DAOException;
import by.training.notebook.dao.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Aliaksandr_Harmaza on 10/6/2016.
 */
public class NoteDAO implements INoteDAO {

    private final static String ADD_NOTE_QUERY = "INSERT INTO Note (createdDate, content, userID) VALUES (?, ?, ?)";
    private final static String UPDATE_NOTE_QUERY = "UPDATE INTO Note SET createdDate = ?, content = ?, userID = ? WHERE id = ?";
    private final static String SEARCH_BY_CONTENT_QUERY = "SELECT * FROM Note WHERE content = ? AND userID = ?";
    private final static String SEARCH_BY_CREATED_DATE_QUERY = "SELECT * FROM Note WHERE " +
            "YEAR(createdDate) = YEAR(?) AND DAYOFYEAR(createdDate) = DAYOFYEAR(?) AND userID = ?";
    private final static String DELETE_BY_ID_AND_USER_ID_QUERY = "DELETE FROM Note where id = ? AND userID = ?";
    private final static String DELETE_BY_USER_ID_QUERY = "DELETE FROM Note WHERE userID = ?";
    private final static String GET_ALL_NOTES_BY_USER_QUERY = "SELECT id, createdDate, content FROM Note WHERE userID = ?";


    @Override
    public ShortNote[] getNoteList(long userID, ConnectionPool.Connection c) throws DAOException {
        if (c == null){
            throw new DAOException("Connection is null");
        }

        ShortNote[] result;
        try (PreparedStatement s = c.prepareStatement(GET_ALL_NOTES_BY_USER_QUERY)) {
            s.setLong(1, userID);
            result = convertToShortNoteArray(s.executeQuery());
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

        return result;
    }

    @Override
    public void saveNote(Note note, ConnectionPool.Connection c) throws DAOException {
        if (note == null){
            throw new DAOException("Note is null");
        }
        if (c == null){
            throw new DAOException("Connection is null");
        }
        try (PreparedStatement s = c
                .prepareStatement((note.getId() == null) ? ADD_NOTE_QUERY : UPDATE_NOTE_QUERY)) {
            s.setTimestamp(1, new Timestamp(note.getCreatedDate().getTime()));
            s.setString(2, note.getContent());
            s.setLong(3, note.getUserID());

            if (note.getId() != null){
                s.setLong(4, note.getId());
            }
            s.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteNote(long noteID, long userID, ConnectionPool.Connection c) throws DAOException {
        if (c == null){
            throw new DAOException("Connection is null");
        }
        try (PreparedStatement s = c.prepareStatement(DELETE_BY_ID_AND_USER_ID_QUERY)) {
            s.setLong(1, noteID);
            s.setLong(2, userID);
            s.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteNote(long userID, ConnectionPool.Connection c) throws DAOException {
        if (c == null){
            throw new DAOException("Connection is null");
        }
        try (PreparedStatement s = c.prepareStatement(DELETE_BY_USER_ID_QUERY)) {
            s.setLong(1, userID);
            s.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public ShortNote[] searchByCreatedDate(Date createdDate, long userID, ConnectionPool.Connection c) throws DAOException {
        if (createdDate == null){
            throw new DAOException("Content is null");
        }
        if (c == null){
            throw new DAOException("Connection is null");
        }

        ShortNote[] result;
        int offset = Calendar.getInstance().getTimeZone().getOffset(createdDate.getTime());
        try (PreparedStatement s = c.prepareStatement(SEARCH_BY_CREATED_DATE_QUERY)) {
            s.setTimestamp(1, new Timestamp(createdDate.getTime() + offset));
            s.setTimestamp(2, new Timestamp(createdDate.getTime() + offset));
            s.setLong(3, userID);
            result = convertToShortNoteArray(s.executeQuery());
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

        return result;
    }

    @Override
    public ShortNote[] searchByContent(String content, long userID, ConnectionPool.Connection c) throws DAOException {
        if (content == null){
            throw new DAOException("Content is null");
        }
        if (c == null){
            throw new DAOException("Connection is null");
        }

        ShortNote[] result;
        try (PreparedStatement s = c.prepareStatement(SEARCH_BY_CONTENT_QUERY)) {
            s.setString(1, content);
            s.setLong(2, userID);
            result = convertToShortNoteArray(s.executeQuery());
        } catch (SQLException ex) {
            throw new DAOException(ex.getMessage(), ex);
        }

        return result;
    }


    private ShortNote[] convertToShortNoteArray(ResultSet resultSet) throws SQLException {
        List<ShortNote> result = new ArrayList<>();
        while (resultSet.next()){
            ShortNote note = new ShortNote();
            note.setId(resultSet.getLong("id"));
            note.setCreatedDate(new Date(resultSet.getTimestamp("createdDate").getTime()));
            note.setContent(resultSet.getString("content"));
            result.add(note);
        }
        return result.toArray(new ShortNote[result.size()]);
    }

}
