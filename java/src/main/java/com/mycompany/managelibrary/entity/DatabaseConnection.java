package com.mycompany.managelibrary.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String JDBC_URL = "jdbc:postgresql://pg-28f387a6-oopjava19.i.aivencloud.com:24516/users?sslmode=require";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}
