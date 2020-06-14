package com.loginform.filters;

import com.loginform.model.UserDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter("/UserRegisterServlet")
public class UserRegisterFilter implements Filter {
    private String namePattern = "^[A-Z]{1}[a-z]{2,}$";
    private String passwordPattern = "^((?=[^@|#|&|%|$]*[@|&|#|%|$][^@|#|&|%|$]*$)(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9#@$?]{8,})$";
    private String emailPattern="^[A-Za-z]{3,}([.|+|_|-]?[A-Za-z0-9]+)?[@][A-Za-z0-9]+[.][A-Za-z]{2,4}([.][A-Za-z]{2,4}+)?$";
    private String username;
    private String email;
    private String password;
    private String passwordRepeat;
    private RequestDispatcher rd;
    HttpServletRequest request;
    HttpServletResponse response;
    String message;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        request = (HttpServletRequest) servletRequest;
        response = (HttpServletResponse) servletResponse;
        username = request.getParameter("username");
        email = request.getParameter("email");
        password = request.getParameter("password");

        try {
            if(isAllFieldEmpty() && isUserEmailFieldEmpty() && isUserPasswordFieldEmpty() && isEmailPasswordFieldEmpty() &&
                    isNameMatch() && isEmailMatch() && isPasswordMatch() && isPasswordSame() && isUserNotExist()  ) {
                request.setAttribute("message", message);
                filterChain.doFilter(request,response);
            }else{
                request.setAttribute("message", message);
                rd = request.getRequestDispatcher("/register.jsp");
                rd.include(request,response);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private boolean isUserNotExist() throws SQLException {
        if(UserDAO.getUser(email)==null){
            message ="<h3 style=\"color:green\">Successfully Registered</h3>";
            return true;
        }else{
            message ="<h3 style=\"color:red\">User Already Exist</h3>";
            return false;
        }
    }

    private boolean isAllFieldEmpty() {
        if(username.isEmpty() && email.isEmpty() && password.isEmpty() &&  passwordRepeat.isEmpty() ) {
            message="<h3 style=\"color:red\">Fill all fields</h3>";
            return false;
        }
        return true;
    }

    private boolean isUserEmailFieldEmpty() {
        if(username.isEmpty() && email.isEmpty() ) {
            message="<h3 style=\"color:red\">User and Email fields is empty</h3>";
            return false;
        }
        return true;
    }

    private boolean isUserPasswordFieldEmpty() {
        if(username.isEmpty() && password.isEmpty() ) {
            message="<h3 style=\"color:red\">User and Password fields is empty</h3>";
            return false;
        }
        return true;
    }

    private boolean isEmailPasswordFieldEmpty() {
        if( email.isEmpty() && password.isEmpty() ) {
            message="<h3 style=\"color:red\">Email and password fields is empty</h3>";
            return false;
        }
        return true;
    }

    private boolean isNameMatch() {
        if (!username.matches(namePattern)) {
            message ="<h3 style=\"color:red\">Username is not valid</h3>";
            return false;
        }
        return true;
    }

    private boolean isEmailMatch() {
        if (!email.matches(emailPattern)){
            message ="<h3 style=\"color:red\">Email is not valid</h3>";
            return false;
        }
        return true;
    }

    private boolean isPasswordMatch() {
        if (!password.matches(passwordPattern)) {
            message ="<h3 style=\"color:red\">Password is not valid</h3>";
            return false;
        }
        return true;
    }

    private boolean isPasswordSame() {
        if (!password.equals(passwordRepeat)) {
            message ="<h3 style=\"color:red\">Password is not match</h3>";
            return false;
        }
        return true;
    }

    @Override
    public void destroy() {
    }
}
