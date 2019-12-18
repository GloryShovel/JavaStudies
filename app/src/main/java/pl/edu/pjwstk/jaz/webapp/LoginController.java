package pl.edu.pjwstk.jaz.webapp;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.edu.pjwstk.jaz.auth.ProfileEntity;
import pl.edu.pjwstk.jaz.dbstuff.User;
import pl.edu.pjwstk.jaz.login.LoginRequest;
import pl.edu.pjwstk.jaz.login.Register;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;
import java.util.List;

@Named
@RequestScoped
public class LoginController {
    @Inject
    private LoginRequest loginRequest;
    @Inject
    private Register register;
    @PersistenceContext
    private EntityManager entityManager;

    //making references
    private FacesContext context = FacesContext.getCurrentInstance();

    public String login() {
        //passwordEncoder is needed for later compare password from form an DB
        var passwordEncoder = new BCryptPasswordEncoder();

        //DB mapping
        //makes query to DB and return as list type of User from profile (somehow, probably by type)
        var list = entityManager.createQuery("from User where login = :login", User.class)
                .setParameter("login", loginRequest.getUsername())
                .getResultList();

        //DEV STUFF
//        System.out.println(list.get(0).getName());

        //login if list is not empty
        if(list.isEmpty()){
            return "/login.xhtml";
        }else {
            //Checking password for username
            if (passwordEncoder.matches(loginRequest.getPassword(), list.get(0).getPswrd())) {
                //Reference to old session
                HttpSession oldSession = (HttpSession) context.getExternalContext().getSession(false);

                //Destroying old session
                if (oldSession != null) {
                    oldSession.invalidate();
                }

                //Creating new session for actually logged user
                HttpSession newSession = (HttpSession) context.getExternalContext().getSession(true);

                //setting attributes for existing session (later just set attribute ID)
                newSession.setAttribute("username", loginRequest.getUsername());
                newSession.setAttribute("password", loginRequest.getPassword());
                //setting lifetime of session for 5min
                newSession.setMaxInactiveInterval(5 * 60);

                //console log for debugging
                System.out.println("Loged in");
                if(loginRequest.getUsername().equals("admin")){
                    return "/indexadmin.xhtml";
                }else {
                    return "/index.xhtml";
                }
            } else {
                //console log for debugging
                System.out.println("Not Logged in");
                return "/login.xhtml";
            }
        }

        //DEV STUFF
        //System.out.println("Tried to log in using " + loginRequest.toString());
    }

    public String logout(){

        HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
        session.invalidate();

        return "/login.xhtml";
    }
}
