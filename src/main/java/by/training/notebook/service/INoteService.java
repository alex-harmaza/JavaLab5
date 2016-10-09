package by.training.notebook.service;

import by.training.notebook.bean.entity.ShortNote;
import by.training.notebook.service.exception.ServiceException;

import java.util.Date;

/**
 * Created by Aliaksandr_Harmaza on 10/6/2016.
 */
public interface INoteService {

    ShortNote[] showNotesByUser() throws ServiceException;
    void addNote(String note) throws ServiceException;
    void deleteNoteByID(long id) throws ServiceException;
    void deleteAllNotes() throws ServiceException;
    ShortNote[] searchByCreatedDate(Date createdDate) throws ServiceException;
    ShortNote[] searchByContent(String content) throws ServiceException;

}
