package pl.edu.pjwstk.jaz.login;

import javax.faces.application.ResourceHandler;
import javax.faces.context.FacesContext;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter("*")
public class LoginFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        //Storing URIs
        String indexURI = req.getContextPath() + "/index.xhtml";
        String loginURI = req.getContextPath() + "/login.xhtml";
        String registerURI = req.getContextPath() + "/register.xhtml";
        String uri = req.getRequestURI();

//        //DEV STUFF
//        System.out.println(indexURI);
//        System.out.println(loginURI);
//        System.out.println(registerURI);

        //Storing booleans for filtering
        boolean loggedIn = req.getSession(false) != null && req.getSession(false).getAttribute("username") != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        boolean registerRequest = req.getRequestURI().equals(registerURI);
        boolean resourceRequest = req.getRequestURI().startsWith(req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER);

//        //DEV STUFF
//        System.out.println("is logged: "+loggedIn);
//        System.out.println("login: "+loginRequest);
//        System.out.println("register: "+registerRequest);


        //NOTE: if you want do do filtering you need to make huge if/else
        //redirect to index if logged in and request to login or register
        if(loggedIn && loginRequest || loggedIn && registerRequest){
            res.sendRedirect(indexURI);
        }
        //let through if
        //loggedIn - everywhere
        //loginRequest - to login even if logged in
        //registerRequest - to register even if logged in
        //resourceRequest - for every resource that request try to use
        else if(loggedIn || loginRequest || registerRequest || resourceRequest){
            chain.doFilter(req, res);
        }
        //else not logged in so redirect to login
        else{
            res.sendRedirect(loginURI);
        }

    }
}
