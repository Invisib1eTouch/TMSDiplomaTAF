package models;

import com.opencsv.bean.CsvBindByName;

public class User {

    public User(String login, String password) {
        this.login = login;
        this.password = password;
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

    @CsvBindByName(column = "login")
    public String login;

    @CsvBindByName(column = "password")
    public String password;
}
