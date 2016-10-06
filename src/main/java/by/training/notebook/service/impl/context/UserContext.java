package by.training.notebook.service.impl.context;

/**
 * Created by Aliaksandr_Harmaza on 10/5/2016.
 */
public class UserContext {

    private static final UserContext instance = new UserContext();

    private Long id;
    private String login;
    private UserTypeEnum type;


    public static UserContext getInstance(){
        return instance;
    }


    private UserContext(){
        setType(UserTypeEnum.ANONYMOUS);
    }


    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserTypeEnum getType() {
        return type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setType(UserTypeEnum type) {
        if (type == null) {
            throw new NullPointerException("Type is null");
        }
        this.type = type;
    }

    public void invalidate(){
        setType(UserTypeEnum.ANONYMOUS);
        setLogin(null);
        setId(null);
    }
}