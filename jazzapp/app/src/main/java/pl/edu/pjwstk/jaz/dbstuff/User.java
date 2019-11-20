package pl.edu.pjwstk.jaz.dbstuff;

import javax.persistence.*;

@Entity
@Table(name = "profile")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String sname;
    private String login;
    private String pswrd;
    private String email;
    private String bday;

    //Needs to be here
    public User(){
    }

    //Commonly used to construct
    public User(String name, String sname, String login, String pswrd, String email, String bday){
        this.name = name;
        this.sname = sname;
        this.login = login;
        this.pswrd = pswrd;
        this.email = email;
        this.bday = bday;
    }

    //Setters
    //**************************************************************************************************
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPswrd(String pswrd) { this.pswrd = pswrd; }
    //Getters
    //**************************************************************************************************

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSname() {
        return sname;
    }

    public String getLogin() {
        return login;
    }

    public String getPswrd() { return pswrd; }

    public String getEmail() {
        return email;
    }

    public String getBday() {
        return bday;
    }

    //**************************************************************************************************
}
