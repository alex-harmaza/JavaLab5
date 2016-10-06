package by.training.notebook.dao.mysql;

import by.training.notebook.bean.entity.User;
import by.training.notebook.dao.IUserDAO;
import by.training.notebook.dao.exception.DAOException;
import by.training.notebook.dao.pool.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alexh on 05.10.2016.
 */
public class UserDAO implements IUserDAO {

    private final static String LOGIN_QUERY = "SELECT COUNT(id) FROM USER WHERE login = ? and password = ?";
    private final static String REGISTER_QUERY = "INSERT INTO User(login, password) VALUES (?, ?)";
    private final static String DELETE_QUERY = "DELETE FROM User WHERE login = ? and password = ?";


    @Override
    public boolean login(User user, ConnectionPool.Connection connection) throws DAOException {
        boolean result;
        try (PreparedStatement statement = connection.prepareStatement(LOGIN_QUERY)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            result = resultSet.getLong(0) != 0;
        }
        catch (SQLException ex){
            throw new DAOException(ex.getMessage(), ex);
        }
        return result;
    }

    @Override
    public void register(User user, ConnectionPool.Connection connection) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(REGISTER_QUERY)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.execute();
        }
        catch (SQLException ex){
            throw new DAOException(ex.getMessage(), ex);
        }
    }

    @Override
    public void delete(User user, ConnectionPool.Connection connection) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.execute();
        }
        catch (SQLException ex){
            throw new DAOException(ex.getMessage(), ex);
        }
    }
}
