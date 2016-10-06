package by.training.notebook.dao.mysql;

import by.training.notebook.bean.entity.Note;
import by.training.notebook.dao.INoteDAO;
import by.training.notebook.dao.exception.DAOException;
import by.training.notebook.dao.pool.ConnectionPool;
import by.training.notebook.source.UserContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alexh on 05.10.2016.
 */
public class NoteDAO implements INoteDAO {

    private final static String ADD_NOTE_QUERY = "INSERT INTO Note(createdDate, message, userID) VALUES (?, ?, ?)";
    private final static String DELETE_NOTE_QUERY = "DELETE FROM Note WHERE id = ? AND userID = ?";
    private final static String GET_NOTE_LIST_QUERY = "SELECT id, creationDate, message FROM Note WHERE userID = ?";
    private final static String SEARCH_BY_CREATED_DATE_QUERY = "SELECT id, creationDate, message FROM Note WHERE creationDate <= ? and userID = ?";
    private final static String SEARCH_BY_CONTENT_QUERY = "SELECT id, creationDate, message FROM Note WHERE message = ? and userID = ?";


    @Override
    public void addNote(Note note, ConnectionPool.Connection connection) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(ADD_NOTE_QUERY)) {
            statement.setLong(1, note.getCreationDate().getTime());
            statement.setString(2, note.getMessage());
            statement.setLong(3, UserContext.getInstance().getId());
            statement.execute();
        }
        catch (SQLException ex){
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteNoteByID(long noteID, ConnectionPool.Connection connection) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_NOTE_QUERY)) {
            statement.setLong(1, noteID);
            statement.setLong(2, UserContext.getInstance().getId());
            statement.execute();
        }
        catch (SQLException ex){
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public Note[] getNoteList(ConnectionPool.Connection connection) throws DAOException {
        List<Note> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_NOTE_LIST_QUERY)) {
            statement.setLong(1, UserContext.getInstance().getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.add(new Note(
                        resultSet.getLong("id"),
                        new Date(resultSet.getLong("createdDate")),
                        resultSet.getString("message")));
            }
        }
        catch (SQLException ex){
            throw new DAOException(ex.getMessage(), ex);
        }
        return result.toArray(new Note[result.size()]);
    }

    @Override
    public Note[] getNoteList(Date createdDate, ConnectionPool.Connection connection) throws DAOException {
        List<Note> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SEARCH_BY_CREATED_DATE_QUERY)) {
            statement.setLong(1, createdDate.getTime());
            statement.setLong(2, UserContext.getInstance().getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.add(new Note(
                        resultSet.getLong("id"),
                        new Date(resultSet.getLong("createdDate")),
                        resultSet.getString("message")));
            }
        }
        catch (SQLException ex){
            throw new DAOException(ex.getMessage(), ex);
        }
        return result.toArray(new Note[result.size()]);
    }

    @Override
    public Note[] getNoteList(String content, ConnectionPool.Connection connection) throws DAOException {
        List<Note> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SEARCH_BY_CONTENT_QUERY)) {
            statement.setString(1, content);
            statement.setLong(2, UserContext.getInstance().getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                result.add(new Note(
                        resultSet.getLong("id"),
                        new Date(resultSet.getLong("createdDate")),
                        resultSet.getString("message")));
            }
        }
        catch (SQLException ex){
            throw new DAOException(ex.getMessage(), ex);
        }
        return result.toArray(new Note[result.size()]);
    }

}
