package by.training.notebook.utility;

import java.sql.*;
import java.util.Date;

/**
 * Created by alexh on 08.10.2016.
 */
public class MySQLQuery {

    private final static String INSERT_USER = "INSERT INTO User (login, password) VALUES ('%s','%s')";
    private final static String SELECT_USER_ID = "SELECT id FROM User WHERE login = '%s' AND password = '%s'";
    private final static String DELETE_USER = "DELETE FROM User WHERE login = '%s' AND password = '%s'";
    private final static String GET_USER_COUNT = "SELECT COUNT(id) FROM User WHERE login = '%s' and password = '%s'";
    private final static String INSERT_NOTE = "INSERT INTO Note (createdDate, content, userID) VALUES (?, ?, ?)";
    private final static String DELETE_NOTE = "DELETE FROM Note WHERE content = '%s' AND userID = '%d'";
    private final static String GET_NOTE_COUNT = "SELECT COUNT(id) FROM Note WHERE content = '%s' AND userID = %d";
    private final static String GET_NOTE_ID = "SELECT id FROM Note WHERE content = '%s' AND userID = %d";


    public static void addUser(String login, String password, Connection connection) throws SQLException {
        if (login == null){
            throw new NullPointerException("Login is null");
        }
        if (password == null){
            throw new NullPointerException("Password is null");
        }
        if (connection == null){
            throw new NullPointerException("Connection is null");
        }
        try (Statement statement = connection.createStatement();) {
            statement.executeUpdate(String.format(INSERT_USER, login, password));
        }
    }

    public static Long getUserID(String login, String password, Connection connection) throws SQLException {
        if (login == null){
            throw new NullPointerException("Login is null");
        }
        if (password == null){
            throw new NullPointerException("Password is null");
        }
        if (connection == null){
            throw new NullPointerException("Connection is null");
        }

        Long id = null;
        try (Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(String.format(SELECT_USER_ID, login, password));
            while (set.next()){
                id = set.getLong(1);
            }
        }

        return id;
    }

    public static int deleteUser(String login, String password, Connection connection) throws SQLException {
        if (login == null){
            throw new NullPointerException("Login is null");
        }
        if (password == null){
            throw new NullPointerException("Password is null");
        }
        if (connection == null){
            throw new NullPointerException("Connection is null");
        }

        int result;
        try (Statement statement = connection.createStatement()) {
            result = statement.executeUpdate(String.format(DELETE_USER, login, password));
        }

        return result;
    }

    public static long getUserCount(String login, String password, Connection connection) throws SQLException {
        if (login == null){
            throw new NullPointerException("Login is null");
        }
        if (password == null){
            throw new NullPointerException("Password is null");
        }
        if (connection == null){
            throw new NullPointerException("Connection is null");
        }

        long result;
        try (Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(String.format(GET_USER_COUNT, login, password));
            set.next();
            result = set.getLong(1);
        }

        return result;
    }

    public static void addNote(Date createdDate, String content, Long userID, Connection connection) throws SQLException {
        if (createdDate == null){
            throw new NullPointerException("createdDate is null");
        }
        if (content == null){
            throw new NullPointerException("Content is null");
        }
        if (connection == null){
            throw new NullPointerException("Connection is null");
        }

        try (PreparedStatement statement = connection.prepareStatement(INSERT_NOTE)) {
            statement.setTimestamp(1, new Timestamp(createdDate.getTime()));
            statement.setString(2, content);
            statement.setLong(3, userID);
            statement.executeUpdate();
        }
    }

    public static Long getNoteID(String content, long userID, Connection connection) throws SQLException {
        if (content == null){
            throw new NullPointerException("Content is null");
        }
        if (connection == null){
            throw new NullPointerException("Connection is null");
        }

        Long id = null;
        try (Statement s = connection.createStatement()) {
            ResultSet set = s.executeQuery(String.format(GET_NOTE_ID, content, userID));
            while (set.next()){
                id = set.getLong(1);
            }
        }

        return id;
    }

    public static long getNoteCount(String content, long userID, Connection connection) throws SQLException {
        if (content == null){
            throw new NullPointerException("Content is null");
        }
        if (connection == null){
            throw new NullPointerException("Connection is null");
        }

        long result;
        try (Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery(String.format(GET_NOTE_COUNT, content, userID));
            set.next();
            result = set.getLong(1);
        }

        return result;
    }

}
