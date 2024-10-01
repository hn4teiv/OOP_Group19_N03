package com.mycompany.managelibrary.controller;

import com.mycompany.managelibrary.entity.User;
import com.mycompany.managelibrary.dao.UserDao;
import com.mycompany.managelibrary.view.BookView;
import com.mycompany.managelibrary.view.LoanReturnView;
import com.mycompany.managelibrary.view.LoginView;
import javax.swing.JOptionPane;

public class LoginController {
    private UserDao userDao;
    private LoginView loginView;
    private BookView bookView;
    private LoanReturnView loanReturnView;

    public LoginController(LoginView view) {
        this.loginView = view;
        this.userDao = new UserDao();
        this.bookView = new BookView();
        this.loanReturnView = new LoanReturnView();

        loginView.addLoginListener(new LoginListener());
    }

    public void showBookView() {
        try {
            bookView.setVisible(true); // Hiển thị giao diện sách
            loginView.dispose(); // Đóng giao diện đăng nhập
        } catch (Exception e) {
            // Thông báo lỗi khi không thể hiển thị giao diện sách
            JOptionPane.showMessageDialog(null, "Lỗi khi mở giao diện sách: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    class LoginListener implements LoginView.LoginListener {
        public void onLogin() {
            try {
                User user = loginView.getUser();
                if (userDao.checkUser(user)) {
                    showBookView();
                } else {
                    loginView.showMessage("Tên đăng nhập hoặc mật khẩu không chính xác!");
                }
            } catch (Exception e) {
                // Thông báo lỗi khi có vấn đề trong quá trình đăng nhập
                JOptionPane.showMessageDialog(null, "Lỗi khi đăng nhập: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
