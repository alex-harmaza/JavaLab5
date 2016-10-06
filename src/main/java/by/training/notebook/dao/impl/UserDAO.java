package by.training.notebook.dao.impl;

import by.training.notebook.bean.entity.User;
import by.training.notebook.dao.IUserDAO;
import by.training.notebook.dao.exception.DAOException;
import by.training.notebook.dao.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Aliaksandr_Harmaza on 10/6/2016.
 */
public class UserDAO implements IUserDAO{

    private final static String GET_USER_QUERY = "SELECT * FROM User where login = ?";
    private final static String ADD_USER_QUERY = "INSERT INTO User (login, password) VALUES (?, ?)";
    private final static String UPDATE_USER_QUERY = "UPDATE FROM User set login = ? password = ? where id = ?";


    @Override
    public User getUser(String login, ConnectionPool.Connection connection) throws DAOException {
        if (login == null){
            throw new DAOException("Login is null");
        }
        if (connection == null){
            throw new DAOException("Connection is null");
        }

        User user = null;
        try (PreparedStatement s = connection.prepareStatement(GET_USER_QUERY)) {
            s.setString(1, login);
            ResultSet resultSet = s.executeQuery();

            while (resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }

        return user;
    }

    @Override
    public void addUser(User user, ConnectionPool.Connection connection) throws DAOException {
        if (user == null){
            throw new DAOException("User is null");
        }
        if (connection == null){
            throw new DAOException("Connection is null");
        }

        try (PreparedStatement s = connection
                .prepareStatement((user.getId() == null)? ADD_USER_QUERY : UPDATE_USER_QUERY)) {
            s.setString(1, user.getLogin());
            s.setString(2, user.getPassword());

            if (user.getId() != null){
                s.setLong(3, user.getId());
            }
            s.executeUpdate();
        }
        catch (SQLException ex){
            throw new DAOException(ex);
        }
    }

    @Override
    public void deleteUser(User user, ConnectionPool.Connection connection) throws DAOException {
        if (user == null){
            throw new DAOException("User is null");
        }
        if (connection == null){
            throw new DAOException("Connection is null");
        }

        StringBuilder builder = new StringBuilder();

        if (user.getId() != null){
            builder.append("id = ?");
        }
        if (user.getLogin() != null){
            builder.append((builder.length() == 0) ? "login = ?" : "AND login = ?");
        }
        if (user.getPassword() != null){
            builder.append((builder.length() == 0) ? "password = ?" : "AND password = ?");
        }

        builder.insert(0, "DELETE_USER FROM User ");

        try (PreparedStatement s = connection.prepareStatement(builder.toString())) {
            if (user.getId() != null){
                s.setLong(1, user.getId());
            }
            if (user.getLogin() != null){
                s.setString(2, user.getLogin());
            }
            if (user.getPassword() != null){
                s.setString(3, user.getPassword());
            }
            s.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }
}
