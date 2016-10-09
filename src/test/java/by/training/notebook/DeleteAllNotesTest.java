package by.training.notebook;

import by.training.notebook.bean.Request;
import by.training.notebook.bean.RequestWithContent;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.ResponseWithMessage;
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
public class DeleteAllNotesTest extends ControllerTest {

    private final static String USER_LOGIN = "deleteAllNotesTest login";
    private final static String USER_PASSWORD = "deleteAllNotesTest password";
    private final static String NOTE_CONTENT = "deleteAllNotesTest content";


    @Override @Test
    public void checkResponseWithInvalidRequest() {
        Response response = getController()
                .doRequest(new RequestWithContent(CommandEnum.DELETE_ALL_NOTES, "test"));
        assertNotNull(response, "Response is null");
        assertFalse(response.isStatus(), "Incorrect response status");
        assertEquals(response.getClass(), ResponseWithMessage.class, "Incorrect response type");
    }

    @Test(priority = 0)
    public void checkResponseIfNotAuthorizedUser(){
        Response response = getController().doRequest(new Request(CommandEnum.DELETE_ALL_NOTES));
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

        MySQLQuery.addNote(new Date(),NOTE_CONTENT, userContext.getId(), getConnection());

        Response response = getController().doRequest(new Request(CommandEnum.DELETE_ALL_NOTES));
        assertNotNull(response, "Response is null");
        assertTrue(response.isStatus(), "Incorrect response status");
        assertEquals(response.getClass(), ResponseWithMessage.class, "Incorrect response type");

        long noteCount = MySQLQuery.getNoteCount(NOTE_CONTENT, userContext.getId(), getConnection());
        assertTrue(noteCount == 0, "The note is not removed from the database");
    }

    @AfterClass
    public void deleteUser() throws SQLException {
        UserContext.getInstance().invalidate();
        MySQLQuery.deleteUser(USER_LOGIN, USER_PASSWORD, getConnection());
    }
}
