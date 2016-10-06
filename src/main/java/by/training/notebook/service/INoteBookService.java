package by.training.notebook.service;

import by.training.notebook.bean.entity.Note;
import by.training.notebook.bean.entity.User;
import by.training.notebook.service.exception.ServiceException;

import java.util.Date;

/**
 * Created by alexh on 05.10.2016.
 */
public interface INoteBookService {

    void addNote(String note) throws ServiceException;
    void deleteNote(long id) throws ServiceException;
    Note[] searchByCreatedDate(Date createdDate) throws ServiceException;
    Note[] searchByNoteContent(String content) throws ServiceException;

    boolean login(User user) throws ServiceException;
    void registerUser(User user) throws ServiceException;
    void deleteUser(User user) throws ServiceException;
}
