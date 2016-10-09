package by.training.notebook;

import by.training.notebook.controller.Controller;
import by.training.notebook.source.ConfigProvider;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by alexh on 08.10.2016.
 */
public abstract class ControllerTest extends Assert {

    private Controller controller;
    private Connection connection;


    public Connection getConnection(){
        return connection;
    }

    public Controller getController(){
        return controller;
    }


    @BeforeClass
    public void beforeClass() throws ClassNotFoundException, SQLException {
        controller = new Controller();

        ConfigProvider config = ConfigProvider.getInstance();
        connection = DriverManager.getConnection(
                config.getProperty("database.url"),
                config.getProperty("database.name"),
                config.getProperty("database.password")
        );
        connection.setReadOnly(false);
    }

    @AfterClass
    public void afterClass() throws SQLException {
        connection.close();
    }


    public abstract void checkResponseWithInvalidRequest();

    public abstract void checkForCorrectExecution() throws SQLException;
}
