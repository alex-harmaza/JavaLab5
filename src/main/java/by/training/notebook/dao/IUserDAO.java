package by.training.notebook.dao;

import by.training.notebook.bean.entity.User;
import by.training.notebook.dao.exception.DAOException;
import by.training.notebook.dao.pool.ConnectionPool;

/**
 * Created by alexh on 05.10.2016.
 */
public interface IUserDAO {

    User getUser(String login, ConnectionPool.Connection connection) throws DAOException;
    void saveUser(User user, ConnectionPool.Connection connection) throws DAOException;
    void deleteUser(long userID, ConnectionPool.Connection connection) throws DAOException;

}
