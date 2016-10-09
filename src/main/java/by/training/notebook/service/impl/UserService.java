package by.training.notebook.service.impl;

import by.training.notebook.bean.entity.User;
import by.training.notebook.dao.exception.DAOException;
import by.training.notebook.dao.factory.DAOFactory;
import by.training.notebook.dao.pool.ConnectionPool;
import by.training.notebook.service.IUserService;
import by.training.notebook.service.exception.ServiceException;
import by.training.notebook.service.impl.context.UserContext;
import by.training.notebook.service.impl.context.UserTypeEnum;
import by.training.notebook.service.impl.utility.SHA256;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Created by Aliaksandr_Harmaza on 10/6/2016.
 */
public class UserService implements IUserService {

    private final UserContext userContext = UserContext.getInstance();


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
            ConnectionPool pool = ConnectionPool.getInstance();
            ConnectionPool.Connection c = pool.takeConnection();
            try {
                user = DAOFactory.getInstance().getUserDAO().getUser(login, c);
            }
            finally {
                pool.returnConnection(c);
            }
        } catch (InterruptedException | SQLException | DAOException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }

        String encryptedPassword;
        try {
            encryptedPassword = SHA256.convert(password);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            throw new ServiceException(ex.getMessage(), ex);
        }

        boolean result = false;
        if (user != null && user.getPassword().equals(encryptedPassword)){
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
            ConnectionPool pool = ConnectionPool.getInstance();
            ConnectionPool.Connection c = pool.takeConnection(false);
            try {
                DAOFactory.getInstance().getUserDAO()
                        .saveUser(new User(login, SHA256.convert(password)), c);
            }
            finally {
                pool.returnConnection(c);
            }
        }
        catch (InterruptedException | SQLException | DAOException
                | NoSuchAlgorithmException |UnsupportedEncodingException ex){
            throw new ServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public void deleteCurrentUser() throws ServiceException {
        if (userContext.getType() == UserTypeEnum.ANONYMOUS){
            throw new ServiceException("Unauthorized user");
        }
        try {
            ConnectionPool pool = ConnectionPool.getInstance();
            ConnectionPool.Connection c = pool.takeConnection(false);
            try {
                DAOFactory.getInstance().getUserDAO()
                        .deleteUser(UserContext.getInstance().getId(), c);
            }
            finally {
                pool.returnConnection(c);
            }
            userContext.invalidate();
        }
        catch (InterruptedException | SQLException | DAOException ex){
            throw new ServiceException(ex.getMessage(), ex);
        }
    }
}
