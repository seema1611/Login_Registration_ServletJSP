package com.loginform.servlets;

import com.loginform.model.User;
import com.loginform.model.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
        description = "login Servlet testing",
        urlPatterns= {"/UserLoginServlet"}
)
public class UserLoginServlet extends HttpServlet {
    private String message;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pwd = request.getParameter("password");
        User user = null;
        try {
            user = UserDAO.getUser(email,pwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (user == null){
            message ="<h3 style=\"color:red\"> Password or email id either one or both are wrong</h3>";
            request.setAttribute("message", message);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            rd.include(request,response);
        }else {
            request.setAttribute("user",user);
            request.getRequestDispatcher("success.jsp").forward(request,response);
        }
    }
}
