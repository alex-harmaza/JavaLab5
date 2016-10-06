package by.training.notebook.bean.entity;

/**
 * Created by alexh on 05.10.2016.
 */
public class User {

    private Long id;
    private String login;
    private String password;


    public User(){}

    public User(Long id){
        setId(id);
    }

    public User(String login, String password){
        setLogin(login);
        setPassword(password);
    }

    public User(Long id, String login, String password){
        setId(id);
        setLogin(login);
        setPassword(password);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
