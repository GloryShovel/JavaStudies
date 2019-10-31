package pl.edu.pjwstk.jaz.login;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.HashMap;

@Named
@ApplicationScoped
public class Register {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private String birthday;

    private HashMap <String, String> map = new HashMap<>();

    //Pre existing users
    public Register(){
        map.put("coldy", "123");
    }

    //Registering new users
    public String register(){
        if(map.containsKey(getUsername())) {
            return "/register.xhtml";
        }else{
            map.put(getUsername(), getPassword());
            return "/login.xhtml";
        }
    }

    //Returning password from hashmap for existing username
    public String getValue(String key){
        return map.getOrDefault(key, null);
    }

    //Getters and setters
    //******************************************************************************************************
    //Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Surname
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    //Username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //Password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //E-mail
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Birthday
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    //******************************************************************************************************
}
