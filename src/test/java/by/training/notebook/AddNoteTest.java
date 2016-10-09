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

/**
 * Created by alexh on 08.10.2016.
 */
public class AddNoteTest extends ControllerTest {

    private final static String USER_LOGIN = "addNoteTest login";
    private final static String USER_PASSWORD = "addNoteTest password";
    private final static String NOTE_CONTENT = "addNoteTest content";


    @Override
    public void checkResponseWithInvalidRequest() {
        Response response = getController().doRequest(new Request(CommandEnum.ADD_NOTE));
        assertNotNull(response, "Response is null");
        assertFalse(response.isStatus(), "Incorrect response status");
        assertEquals(response.getClass(), ResponseWithMessage.class, "Incorrect response type");
    }

    @Test(priority = 0)
    public void checkResponseIfNotAuthorizedUser(){
        Response response = getController()
                .doRequest(new RequestWithContent(CommandEnum.ADD_NOTE, NOTE_CONTENT));
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

        Response response = getController()
                .doRequest(new RequestWithContent(CommandEnum.ADD_NOTE, NOTE_CONTENT));
        assertNotNull(response, "Response is null");
        assertTrue(response.isStatus(), "Incorrect response status");
        assertEquals(response.getClass(), ResponseWithMessage.class, "Incorrect response type");

        long noteCount = MySQLQuery.getNoteCount(NOTE_CONTENT, userContext.getId(), getConnection());
        assertTrue(noteCount == 1, "Note is not added to the database");
    }


    @AfterClass
    public void deleteUser() throws SQLException {
        UserContext.getInstance().invalidate();
        MySQLQuery.deleteUser(USER_LOGIN, USER_PASSWORD, getConnection());
    }

}
