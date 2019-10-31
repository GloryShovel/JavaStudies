package pl.edu.pjwstk.jaz.login;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class LoginRequest {
    private String username;
    private String password;

    //Getters and setters
    //******************************************************************************************************
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //******************************************************************************************************

    @Override
    //Re done to string for better debug/console info
    public String toString() {
        return "LoginRequest{username: " + username + ", password: " + password + " }";
    }
}
