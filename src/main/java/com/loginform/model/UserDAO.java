package com.loginform.model;

import com.loginform.connection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private static Connection con;
    private static PreparedStatement ps;

    public static User getUser(String email, String pwd) throws SQLException {
        User user = null;
        con= DBConnection.getConnection();
        ps=con.prepareStatement("select * from seema.customer where email=? and password=?");
        ps.setString(1,email);
        ps.setString(2,pwd);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            user=new User();
            user.setName(resultSet.getString(1));
            user.setPassword(resultSet.getString(2));
        }
        return user;
    }

    public static int insertUser(String username,String email,String password) throws SQLException {
        con= DBConnection.getConnection();
        ps=con.prepareStatement("insert into seema.customer(username,email,password) values(?,?,?)");
        ps.setString(1,username);
        ps.setString(2,email);
        ps.setString(3,password);
        int status = ps.executeUpdate();
        return status;
    }

    public static User getUser(String email) throws SQLException {
        User user = null;
        con= DBConnection.getConnection();
        ps=con.prepareStatement("select * from seema.customer where email=?");
        ps.setString(1,email);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            user=new User();
            user.setName(resultSet.getString(1));
            user.setPassword(resultSet.getString(2));
        }
        return user;
    }
}