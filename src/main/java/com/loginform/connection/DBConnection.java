package com.loginform.connection;

import java.sql.*;

public class DBConnection implements ConnectionProvider {

    public static Connection getConnection(){
        Connection myCon=null;
        try {
            Class.forName(DRIVER);
            myCon = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
            System.out.println(exception);
        }
        return myCon;
    }
}
