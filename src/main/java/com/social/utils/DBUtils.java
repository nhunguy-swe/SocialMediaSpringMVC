package com.social.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {
    public Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/social_media_db";
        String user = "";
        String pass = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }
}
