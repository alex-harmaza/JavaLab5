package by.training.notebook;

import by.training.notebook.bean.RequestWithContent;
import by.training.notebook.bean.RequestWithID;
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
 * Created by alexh on 08.10.2016.
 */
public class DeleteNoteTest extends ControllerTest {

    private final static String USER_LOGIN = "deleteNoteTest login";
    private final static String USER_PASSWORD = "deleteNoteTest password";
    private final static String NOTE_CONTENT = "deleteNoteTest content";


    @Override @Test
    public void checkResponseWithInvalidRequest() {
        Response response = getController()
                .doRequest(new RequestWithContent(CommandEnum.DELETE_NOTE, "test"));
        assertNotNull(response, "Response is null");
        assertFalse(response.isStatus(), "Incorrect response status");
        assertEquals(response.getClass(), ResponseWithMessage.class, "Incorrect response type");
    }

    @Test(priority = 0)
    public void checkResponseIfNotAuthorizedUser(){
        Response response = getController().doRequest(new RequestWithID(CommandEnum.DELETE_NOTE, 1L));
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
        Long noteID = MySQLQuery.getNoteID(NOTE_CONTENT, userContext.getId(), getConnection());

        Response response = getController().doRequest(new RequestWithID(CommandEnum.DELETE_NOTE, noteID));
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
