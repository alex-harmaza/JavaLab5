package by.training.notebook;

import by.training.notebook.bean.*;
import by.training.notebook.service.impl.context.UserContext;
import by.training.notebook.service.impl.context.UserTypeEnum;
import by.training.notebook.utility.MySQLQuery;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Date;

/**
 * Created by alexh on 09.10.2016.
 */
public class SearchNotesByCreatedDateTest extends ControllerTest {

    private final static String USER_LOGIN = "searchNotesByCreatedDateTest login";
    private final static String USER_PASSWORD = "searchNotesByCreatedDateTest password";
    private final static String NOTE_CONTENT= "searchNotesByCreatedDateTest content";

    @Override @Test
    public void checkResponseWithInvalidRequest() {
        Response response = getController()
                .doRequest(new Request(CommandEnum.SEARCH_BY_CREATED_DATE));
        assertNotNull(response, "Response is null");
        assertFalse(response.isStatus(), "Incorrect response status");
        assertEquals(response.getClass(), ResponseWithMessage.class, "Incorrect response type");
    }

    @Test(priority = 0)
    public void checkResponseIfNotAuthorizedUser(){
        Response response = getController()
                .doRequest(new RequestWithDate(CommandEnum.SEARCH_BY_CREATED_DATE, new Date()));
        assertNotNull(response, "Response is null");
        assertFalse(response.isStatus(), "Incorrect response status");
        assertEquals(response.getClass(), ResponseWithMessage.class, "Incorrect response type");
    }

    @Override @Test(priority = 1)
    public void checkForCorrectExecution() throws SQLException {
        MySQLQuery.addUser(USER_LOGIN, USER_PASSWORD, getConnection());
        UserContext userContext = UserContext.getInstance();
        userContext.setId(MySQLQuery.getUserID(USER_LOGIN, USER_PASSWORD, getConnection()));
        userContext.setType(UserTypeEnum.AUTHORIZED);

        MySQLQuery.addNote(new Date(), NOTE_CONTENT, userContext.getId(), getConnection());

        Response response = getController()
                .doRequest(new RequestWithDate(CommandEnum.SEARCH_BY_CREATED_DATE, new Date()));
        assertNotNull(response, "Response is null");
        assertTrue(response.isStatus(), "Incorrect response status");
        assertEquals(response.getClass(), ResponseWithNoteList.class, "Incorrect response type");

        ResponseWithNoteList temp = (ResponseWithNoteList) response;
        assertTrue(temp.getNoteList().length == 1, "Incorrect number of notes");
    }


    @AfterClass
    public void deleteUser() throws SQLException {
        UserContext.getInstance().invalidate();
        MySQLQuery.deleteUser(USER_LOGIN, USER_PASSWORD, getConnection());
    }
}
