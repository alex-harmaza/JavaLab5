package by.training.notebook.service;


import by.training.notebook.service.exception.ServiceException;

/**
 * Created by Aliaksandr_Harmaza on 10/6/2016.
 */
public interface IUserService {

    boolean login(String login, String password) throws ServiceException;
    void register(String login, String password) throws ServiceException;
    void deleteCurrentUser() throws ServiceException;

}
