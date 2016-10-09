package by.training.notebook;

import by.training.notebook.bean.Request;
import by.training.notebook.bean.RequestWithToken;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.ResponseWithMessage;
import by.training.notebook.service.impl.context.UserContext;
import by.training.notebook.service.impl.context.UserTypeEnum;
import by.training.notebook.service.impl.utility.SHA256;
import by.training.notebook.utility.MySQLQuery;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;


/**
 * Created by alexh on 08.10.2016.
 */
public class LoginUserTest extends ControllerTest {

    private final static String USER_LOGIN = "loginUserTest login";
    private final static String USER_PASSWORD = "loginUserTest password";


    @Override @Test
    public void checkResponseWithInvalidRequest() {
        Response response = getController().doRequest(new Request(CommandEnum.LOGIN_USER));
        assertNotNull(response, "Response is null");
        assertFalse(response.isStatus(), "Incorrect response status");
        assertEquals(response.getClass(), ResponseWithMessage.class, "Incorrect response type");
    }

    @Override @Test
    public void checkForCorrectExecution() throws SQLException {
        String encryptedPassword;
        try {
            encryptedPassword = SHA256.convert(USER_PASSWORD);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }

        MySQLQuery.addUser(USER_LOGIN, encryptedPassword, getConnection());
        Response response = getController()
                .doRequest(new RequestWithToken(CommandEnum.LOGIN_USER, USER_LOGIN, USER_PASSWORD));

        assertNotNull(response, "Response is null");
        assertTrue(response.isStatus(), "Incorrect response status");
        assertEquals(response.getClass(), Response.class, "Incorrect response type");
        assertEquals(UserTypeEnum.AUTHORIZED, UserContext.getInstance().getType(),
                "User has not made in the user context");
    }


    @AfterClass
    public void deleteUser() throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        UserContext.getInstance().invalidate();
        MySQLQuery.deleteUser(USER_LOGIN, SHA256.convert(USER_PASSWORD), getConnection());
    }
}
