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
        String indexadminURI = req.getContextPath() + "/indexadmin.xhtml";
        String loginURI = req.getContextPath() + "/login.xhtml";
        String registerURI = req.getContextPath() + "/register.xhtml";
        String uri = req.getRequestURI();

        String branchListURI = req.getContextPath() + "/samples/branchList.xhtml";
        String addbranchURI = req.getContextPath() + "/samples/addbranch.xhtml";
        String editbranchURI = req.getContextPath() + "/samples/editbranch.xhtml";
        String categoryListURI = req.getContextPath() + "/samples/categoryList.xhtml";
        String addcategoryURI = req.getContextPath() + "/samples/addcategory.xhtml";
        String editcategoryURI = req.getContextPath() + "/samples/editcategory.xhtml";

//        //DEV STUFF
//        System.out.println(indexURI);
//        System.out.println(loginURI);
//        System.out.println(registerURI);

        //Storing booleans for filtering
        boolean loggedIn = req.getSession(false) != null && req.getSession(false).getAttribute("username") != null;
        boolean isAdmin = loggedIn && req.getSession(false).getAttribute("username").equals("admin");
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        boolean registerRequest = req.getRequestURI().equals(registerURI);

        boolean indexadminRequest = req.getRequestURI().equals(indexadminURI);

        boolean branchListRequest = req.getRequestURI().equals(branchListURI);
        boolean addbranchRequest = req.getRequestURI().equals(addbranchURI);
        boolean editbranchRequest = req.getRequestURI().equals(editbranchURI);
        boolean categoryListRequest = req.getRequestURI().equals(categoryListURI);
        boolean addCategoryRequest = req.getRequestURI().equals(addcategoryURI);
        boolean editCategoryRequest = req.getRequestURI().equals(editcategoryURI);

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
        //redirect if not admin
        else if(!isAdmin && branchListRequest || !isAdmin && addbranchRequest || !isAdmin && editbranchRequest ||
                !isAdmin && categoryListRequest || !isAdmin && addCategoryRequest|| !isAdmin && editCategoryRequest ||
                !isAdmin && indexadminRequest){
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
