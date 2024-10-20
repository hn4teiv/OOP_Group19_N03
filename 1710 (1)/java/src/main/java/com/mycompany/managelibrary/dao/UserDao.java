package com.mycompany.managelibrary.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.managelibrary.entity.DatabaseConnection;
import com.mycompany.managelibrary.entity.User;

public class UserDao {
    private static final String JDBC_URL = "jdbc:postgresql://pg-28f387a6-oopjava19.i.aivencloud.com:24516/users?sslmode=require";
    private static final String USERNAME = "avnadmin";
    private static final String PASSWORD = "AVNS_la0WoxjlMLCV0uId8ze";

    // Lấy tất cả người dùng từ cơ sở dữ liệu
    public List<User> getAllUsersFromDb() {
        List<User> users = new ArrayList<>();
        String query = "SELECT username, password FROM users";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                users.add(new User(username, password));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving users from DB: " + e.getMessage());
            e.printStackTrace();
        }

        return users;
    }

    // Đẩy dữ liệu user lên cơ sở dữ liệu
    public void saveUsersToDb(List<User> users) {
        for (User user : users) {
            if (!checkUserExists(user.getUsername())) {
                saveUserToDb(user); // Lưu người dùng vào cơ sở dữ liệu
            } else {
                System.out.println("User " + user.getUsername() + " already exists in the database.");
            }
        }
    }

    // Đăng ký người dùng mới
    public boolean registerUser(User user) {
        if (checkUserExists(user.getUsername())) {
            return false; // Tên đăng nhập đã tồn tại
        }
        saveUserToDb(user);  // Lưu người dùng vào cơ sở dữ liệu
        return true; // Đăng ký thành công
    }

    // Kiểm tra xem tên đăng nhập đã tồn tại chưa
    private boolean checkUserExists(String username) {
        String query = "SELECT 1 FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Trả về true nếu tìm thấy tên đăng nhập
        } catch (SQLException e) {
            System.err.println("Error checking user existence: " + e.getMessage());
            e.printStackTrace();
        }
        return false; // Không tìm thấy tên đăng nhập
    }

    // Kiểm tra đăng nhập người dùng
    public boolean checkUser(User user) {
        String query = "SELECT 1 FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Trả về true nếu có người dùng khớp
        } catch (SQLException e) {
            System.err.println("Error checking user credentials: " + e.getMessage());
            e.printStackTrace();
        }
        return false; // Không tìm thấy người dùng
    }

    // Lưu một người dùng vào cơ sở dữ liệu
    private void saveUserToDb(User user) {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            int rowsAffected = stmt.executeUpdate();  // Thực hiện lệnh INSERT
            System.out.println(rowsAffected + " rows affected for new user: " + user.getUsername());
        } catch (SQLException e) {
            System.err.println("Error saving user to DB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // In ra tất cả người dùng
    public void printAllUsers() {
        List<User> users = getAllUsersFromDb();
        for (User user : users) {
            System.out.println("Username: " + user.getUsername() + ", Password: " + user.getPassword());
        }
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        // Lấy dữ liệu người dùng từ cơ sở dữ liệu
        List<User> usersFromDb = userDao.getAllUsersFromDb();

        // In ra tất cả người dùng
        if (usersFromDb.isEmpty()) {
            System.out.println("Không có người dùng nào trong cơ sở dữ liệu.");
        } else {
            System.out.println("Danh sách người dùng trong cơ sở dữ liệu:");
            for (User user : usersFromDb) {
                System.out.println("Username: " + user.getUsername() + ", Password: " + user.getPassword());
            }
        }

        System.out.println("Dữ liệu đã được lấy từ cơ sở dữ liệu thành công!");
    }

}
