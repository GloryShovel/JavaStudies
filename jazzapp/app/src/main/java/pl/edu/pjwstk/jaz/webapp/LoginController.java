package pl.edu.pjwstk.jaz.webapp;

import pl.edu.pjwstk.jaz.login.LoginRequest;
import pl.edu.pjwstk.jaz.login.Register;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class LoginController {
    @Inject
    private LoginRequest loginRequest;
    @Inject
    private Register register;

    //making references
    private FacesContext context = FacesContext.getCurrentInstance();

    public String login() {

        //Checking if hashmap contain username
        if(register.getValue(loginRequest.getUsername()) == null){
            System.out.println(register.getValue(loginRequest.getUsername()));
            return "/login.xhtml";
        }else{

            //Checking login and password
            if (register.getValue(loginRequest.getUsername()).equals(loginRequest.getPassword())){

                //Reference to old session
                HttpSession oldSession = (HttpSession)context.getExternalContext().getSession(false);

                //Destroying old session
                if(oldSession != null){
                    oldSession.invalidate();
                }

                //Creating new session for actualy logged user
                HttpSession newSession = (HttpSession)context.getExternalContext().getSession(true);

                //setting attributes for existing session (later just set attribute ID)
                newSession.setAttribute("username", loginRequest.getUsername());
                newSession.setAttribute("password", loginRequest.getPassword());
                //setting lifetime of session for 5min
                newSession.setMaxInactiveInterval(5*60);

                //console log for debuging
                System.out.println("Loged in");
                return "/index.xhtml";
            }else{
                //console log for debuging
                System.out.println("Not Logged in");
                return "/login.xhtml";
            }
        }
        //System.out.println("Tried to log in using " + loginRequest.toString());
    }

    public String logout(){

        HttpSession session = (HttpSession)context.getExternalContext().getSession(false);
        session.invalidate();

        return "/login.xhtml";
    }
}
