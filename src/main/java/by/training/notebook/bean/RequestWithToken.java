package by.training.notebook.bean;

import by.training.notebook.CommandEnum;
import by.training.notebook.bean.Request;

/**
 * Created by Aliaksandr_Harmaza on 10/6/2016.
 */
public class RequestWithToken extends Request {

    private String login;
    private String password;


    public RequestWithToken(CommandEnum command, String login, String password){
        super(command);
        setLogin(login);
        setPassword(password);
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
