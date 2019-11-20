package pl.edu.pjwstk.jaz.login;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.edu.pjwstk.jaz.dbstuff.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.*;
import javax.transaction.Transactional;
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

    //EntityManager handles all entities
    @PersistenceContext
    private EntityManager entityManager;

    //Pre existing users
    public Register(){
        map.put("coldy", "123");
    }

    //Registering new users to hash map
    @Transactional
    public String register(){
        //Encoder
        var passwordEncoder = new BCryptPasswordEncoder();
        //Encoding password
        final String codedPassword = passwordEncoder.encode(getPassword());

        //Mapping DB for login check
        final User login = entityManager.find(User.class, 7L);
        var list = entityManager.createQuery("from User where login = :login", User.class)
                .setParameter("login", getUsername())
                .getResultList();

        //Checking does DB contains this username
        if(list.isEmpty()){
            //Creates new user to store in DB
            var user = new User(getName(), getSurname(), getUsername(), codedPassword, getEmail(), getBirthday());

            //Persist new user to Hibernate
            entityManager.persist(user);
            return "/login.xhtml";
        }else{
            return "/register.xhtml";
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
