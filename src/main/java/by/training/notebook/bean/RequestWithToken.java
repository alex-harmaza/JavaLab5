package by.training.notebook.bean;

import by.training.notebook.CommandEnum;

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
        if (login == null || login.trim().isEmpty()){
            throw new IllegalArgumentException("Incorrect login");
        }
        this.login = login.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null){
            throw new IllegalArgumentException("Incorrect password");
        }
        this.password = password;
    }
}
