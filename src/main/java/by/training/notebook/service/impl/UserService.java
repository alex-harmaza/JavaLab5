package by.training.notebook.service.impl;

import by.training.notebook.service.impl.context.UserTypeEnum;
import by.training.notebook.bean.entity.User;
import by.training.notebook.dao.exception.DAOException;
import by.training.notebook.dao.factory.DAOFactory;
import by.training.notebook.dao.pool.ConnectionPool;
import by.training.notebook.service.IUserService;
import by.training.notebook.service.exception.ServiceException;
import by.training.notebook.service.impl.context.UserContext;

import java.sql.SQLException;

/**
 * Created by Aliaksandr_Harmaza on 10/6/2016.
 */
public class UserService implements IUserService {

    @Override
    public boolean login(String login, String password) throws ServiceException {
        if (login == null || login.trim().isEmpty()){
            throw new ServiceException("Incorrect login");
        }
        if (password == null){
            throw new ServiceException("Password is null");
        }

        User user;

        try {
            ConnectionPool.Connection connection = ConnectionPool.getInstance().takeConnection();
            user = DAOFactory.getInstance().getUserDAO().getUser(login, connection);
            ConnectionPool.getInstance().returnConnection(connection);
        } catch (InterruptedException | SQLException | DAOException ex) {
            throw new ServiceException(ex);
        }

        boolean result = false;
        if (user != null && user.getPassword().equals(password)){
            UserContext userContext = UserContext.getInstance();
            userContext.setType(UserTypeEnum.AUTHORIZED);
            userContext.setId(user.getId());
            result = true;
        }

        return result;
    }

    @Override
    public void register(String login, String password) throws ServiceException {
        if (login == null || login.trim().isEmpty()){
            throw new ServiceException("Incorrect login");
        }
        if (password == null){
            throw new ServiceException("Password is null");
        }
        if (password.length() < 4){
            throw new ServiceException("The password must be more than four characters");
        }

        try {
            ConnectionPool.Connection connection = ConnectionPool.getInstance().takeConnection();
            DAOFactory.getInstance().getUserDAO().saveUser(new User(login, password), connection);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        catch (InterruptedException | SQLException | DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public void deleteCurrentUser() throws ServiceException {
        if (UserContext.getInstance().getType() == UserTypeEnum.ANONYMOUS){
            throw new ServiceException("Unauthorized user");
        }

        try {
            ConnectionPool.Connection connection = ConnectionPool.getInstance().takeConnection();
            DAOFactory.getInstance().getUserDAO()
                    .deleteUser(UserContext.getInstance().getId(), connection);
            ConnectionPool.getInstance().returnConnection(connection);
        }
        catch (InterruptedException | SQLException | DAOException ex){
            throw new ServiceException(ex);
        }

        UserContext.getInstance().invalidate();
    }
}
