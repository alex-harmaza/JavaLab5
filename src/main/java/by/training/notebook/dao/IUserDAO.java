package by.training.notebook.dao;

import by.training.notebook.bean.entity.User;
import by.training.notebook.dao.exception.DAOException;
import by.training.notebook.dao.pool.ConnectionPool;

/**
 * Created by alexh on 05.10.2016.
 */
public interface IUserDAO {

    boolean login(User user, ConnectionPool.Connection connection) throws DAOException;
    void register(User user, ConnectionPool.Connection connection) throws DAOException;
    void delete(User user, ConnectionPool.Connection connection) throws DAOException;

}
