package by.training.notebook;

import by.training.notebook.bean.Request;
import by.training.notebook.bean.RequestWithToken;
import by.training.notebook.bean.Response;
import by.training.notebook.bean.ResponseWithMessage;
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
public class RegisterUserTest extends ControllerTest {

    private final static String USER_LOGIN = "registerUserTest";
    private final static String USER_PASSWORD = "registerUserTest";


    @Override @Test
    public void checkResponseWithInvalidRequest() {
        Response response = getController().doRequest(new Request(CommandEnum.REGISTER_USER));
        assertNotNull(response, "Response is null");
        assertFalse(response.isStatus(), "Incorrect response status");
        assertEquals(response.getClass(), ResponseWithMessage.class, "Incorrect response type");
    }

    @Override @Test
    public void checkForCorrectExecution() throws SQLException {
        String encryptedPassword;
        try {
            encryptedPassword = SHA256.convert(USER_PASSWORD);
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }

        Response response = getController().doRequest(new RequestWithToken(
                CommandEnum.REGISTER_USER, USER_LOGIN, USER_PASSWORD));
        assertNotNull(response, "Response return null");
        assertTrue(response.isStatus(), "Incorrect response status");
        assertEquals(response.getClass(), ResponseWithMessage.class, "Incorrect response type");

        long userCount = MySQLQuery.getUserCount(USER_LOGIN, encryptedPassword, getConnection());
        assertTrue(userCount == 1, "User was not added to the database");
    }


    @AfterClass
    public void deleteUser() throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        MySQLQuery.deleteUser(USER_LOGIN, SHA256.convert(USER_PASSWORD), getConnection());
    }
}
