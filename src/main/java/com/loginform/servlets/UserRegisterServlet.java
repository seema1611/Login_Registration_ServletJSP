package com.loginform.servlets;

import com.loginform.model.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            UserDAO.insertUser(username,email,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp");
        rd.include(request,response);
    }
}