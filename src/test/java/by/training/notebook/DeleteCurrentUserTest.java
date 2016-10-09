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
public class DeleteCurrentUserTest extends ControllerTest {

    private final static String USER_LOGIN = "deleteCurrentUserTest login";
    private final static String USER_PASSWORD = "deleteCurrentUserTest password";


    @Override
    public void checkResponseWithInvalidRequest() {
        Response response = getController()
                .doRequest(new RequestWithContent(CommandEnum.DELETE_USER, "test"));
        assertNotNull(response, "Response is null");
        assertFalse(response.isStatus(), "Incorrect response status");
        assertEquals(response.getClass(), ResponseWithMessage.class, "Incorrect response type");
    }

    @Test (priority = 0)
    public void checkResponseIfNotAuthorizedUser(){
        Response response = getController().doRequest(new Request(CommandEnum.DELETE_USER));
        assertNotNull(response, "Response is null");
        assertFalse(response.isStatus(), "Incorrect response status");
        assertEquals(response.getClass(), ResponseWithMessage.class, "Incorrect response type");
    }

    @Override @Test (priority = 1)
    public void checkForCorrectExecution() throws SQLException {
        MySQLQuery.addUser(USER_LOGIN, USER_PASSWORD, getConnection());

        UserContext userContext = UserContext.getInstance();
        userContext.setId(MySQLQuery.getUserID(USER_LOGIN, USER_PASSWORD, getConnection()));
        userContext.setType(UserTypeEnum.AUTHORIZED);

        Response response = getController().doRequest(new Request(CommandEnum.DELETE_USER));
        assertNotNull(response, "Response is null");
        assertTrue(response.isStatus(), "Incorrect response status");
        assertEquals(response.getClass(), ResponseWithMessage.class, "Incorrect response type");

        long userCount = MySQLQuery.getUserCount(USER_LOGIN, USER_PASSWORD, getConnection());
        assertTrue(userCount == 0, "The user is not removed from the database");
    }


    @AfterClass
    public void deleteUser() throws SQLException {
        UserContext.getInstance().invalidate();
        MySQLQuery.deleteUser(USER_LOGIN, USER_PASSWORD, getConnection());
    }

}
