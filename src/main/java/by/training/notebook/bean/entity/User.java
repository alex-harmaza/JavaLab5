package by.training.notebook.bean.entity;

/**
 * Created by alexh on 05.10.2016.
 */
public class User {

    private String login;
    private String password;


    public User(String login, String password){
        setLogin(login);
        setPassword(password);
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login == null){
            throw new NullPointerException("Login is null");
        }
        if (login.trim().isEmpty()){
            throw new IllegalArgumentException("Login is empty");
        }
        this.login = login.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty() || password.length() < 4){
            throw new IllegalArgumentException("Incorrect password");
        }
        this.password = password;
    }
}
