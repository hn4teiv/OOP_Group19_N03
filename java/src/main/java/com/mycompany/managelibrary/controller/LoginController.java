package com.mycompany.managelibrary.controller;

import com.mycompany.managelibrary.entity.User;
import com.mycompany.managelibrary.dao.UserDao;
import com.mycompany.managelibrary.view.BookView;
import com.mycompany.managelibrary.view.LoginView;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;

public class LoginController {
    private UserDao userDao;
    private LoginView loginView;
    private Stage stage;

    // Constructor nhận cả LoginView và Stage
    public LoginController(LoginView view, Stage stage) {
        this.loginView = view;
        this.stage = stage;
        this.userDao = new UserDao();

        // Đăng ký sự kiện đăng nhập từ view
        loginView.addLoginListener(this);
    }

    // Hàm hiển thị giao diện sách khi đăng nhập thành công
    public void showBookView() {
        try {
            // Tạo và mở giao diện BookView
            BookView bookView = new BookView();
            Stage bookStage = new Stage();
            bookView.start(bookStage); // Mở giao diện sách trong một Stage mới

            // Đóng cửa sổ đăng nhập hiện tại
            stage.close();
        } catch (Exception e) {
            loginView.showMessage("Lỗi khi mở giao diện sách: " + e.getMessage());
        }
    }

    // Hàm xử lý đăng nhập
    public void handleLogin() {
        try {
            // Lấy thông tin người dùng từ giao diện đăng nhập
            User user = loginView.getUser();

            // Kiểm tra thông tin đăng nhập từ UserDao
            if (userDao.checkUser(user)) {
                // Ghi thông tin đăng nhập vào tệp login.txt
                writeLoginToFile(user.getUsername(), user.getPassword());

                showBookView(); // Nếu đăng nhập thành công, mở BookView
            } else {
                // Nếu đăng nhập không thành công, thông báo lỗi
                loginView.showMessage("Tên đăng nhập hoặc mật khẩu không chính xác!");
            }
        } catch (Exception e) {
            // Hiển thị thông báo lỗi khi gặp sự cố đăng nhập
            loginView.showMessage("Lỗi khi đăng nhập: " + e.getMessage());
        }
    }

    // Hàm ghi thông tin đăng nhập vào tệp login.txt
    private void writeLoginToFile(String username, String password) {
        String filePath = "login.txt";  // Đảm bảo đường dẫn này đúng với thư mục repo của bạn

        try (FileWriter writer = new FileWriter(filePath, true)) {
            // Ghi thông tin đăng nhập vào tệp
            writer.write("Login: " + username + ", " + password + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
