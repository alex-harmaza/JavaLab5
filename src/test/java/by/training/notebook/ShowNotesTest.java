package by.training.notebook;

import by.training.notebook.bean.*;
import by.training.notebook.service.impl.context.UserContext;
import by.training.notebook.service.impl.context.UserTypeEnum;
import by.training.notebook.utility.MySQLQuery;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by alexh on 09.10.2016.
 */
public class ShowNotesTest extends ControllerTest {

    private final static String FIRST_USER_LOGIN = "showNotesTest login 1";
    private final static String FIRST_USER_PASSWORD = "showNotesTest password 1";
    private final static String SECOND_USER_LOGIN = "showNotesTest login 2";
    private final static String SECOND_USER_PASSWORD = "showNotesTest password 2";
    private final static String FIRST_NOTE_CONTENT = "showNotesTest content 1";
    private final static String SECOND_NOTE_CONTENT = "showNotesTest content 2";


    @Override @Test
    public void checkResponseWithInvalidRequest() {
        Response response = getController().doRequest(new RequestWithDate(CommandEnum.SHOW_NOTES, new Date()));
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
        MySQLQuery.addUser(FIRST_USER_LOGIN, FIRST_USER_PASSWORD, getConnection());
        MySQLQuery.addUser(SECOND_USER_LOGIN, SECOND_USER_PASSWORD, getConnection());
        Long firstUserID = MySQLQuery.getUserID(FIRST_USER_LOGIN, FIRST_USER_PASSWORD, getConnection());
        Long secondUserID = MySQLQuery.getUserID(SECOND_USER_LOGIN, SECOND_USER_PASSWORD, getConnection());

        MySQLQuery.addNote(new Date(), FIRST_NOTE_CONTENT, firstUserID, getConnection());
        MySQLQuery.addNote(new Date(), SECOND_NOTE_CONTENT, secondUserID, getConnection());

        UserContext userContext = UserContext.getInstance();
        userContext.setId(firstUserID);
        userContext.setType(UserTypeEnum.AUTHORIZED);

        Response response = getController().doRequest(new Request(CommandEnum.SHOW_NOTES));
        assertNotNull(response, "Response is null");
        assertTrue(response.isStatus(), "Incorrect response status");
        assertEquals(response.getClass(), ResponseWithNoteList.class, "Incorrect response type");

        ResponseWithNoteList temp = (ResponseWithNoteList) response;
        assertTrue(temp.getNoteList().length == 1, "Incorrect note list size");
        assertEquals(temp.getNoteList()[0].getContent(), FIRST_NOTE_CONTENT, "Incorrect note");
    }


    @AfterClass
    public void deleteUser() throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        UserContext.getInstance().invalidate();
        MySQLQuery.deleteUser(FIRST_USER_LOGIN, FIRST_USER_PASSWORD, getConnection());
        MySQLQuery.deleteUser(SECOND_USER_LOGIN, SECOND_USER_PASSWORD, getConnection());
    }
}
