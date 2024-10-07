package com.mycompany.managelibrary.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/tên_cơ_sở_dữ_liệu"; // Thay thế với tên cơ sở dữ liệu của bạn
    private static final String USER = "postgres"; // Thay thế với tên người dùng của bạn
    private static final String PASSWORD = "mật_khẩu"; // Thay thế với mật khẩu của bạn

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
