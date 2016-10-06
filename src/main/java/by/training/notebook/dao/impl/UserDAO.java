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

    private final static String GET_USER_QUERY = "SELECT * FROM User WHERE login = ?";
    private final static String ADD_USER_QUERY = "INSERT INTO User (login, password) VALUES (?, ?)";
    private final static String UPDATE_USER_QUERY = "UPDATE User SET login = ?, password = ? WHERE id = ?";
    private final static String DELETE_USER_QUERY = "DELETE FROM User WHERE id = ?";


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
    public void saveUser(User user, ConnectionPool.Connection connection) throws DAOException {
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
    public void deleteUser(long userID, ConnectionPool.Connection connection) throws DAOException {
        if (connection == null){
            throw new DAOException("Connection is null");
        }

        try (PreparedStatement s = connection.prepareStatement(DELETE_USER_QUERY)) {
            s.setLong(1, userID);
            s.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }
}
