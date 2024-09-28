package com.mycompany.managelibrary.controller;

import com.mycompany.managelibrary.entity.User;
import com.mycompany.managelibrary.dao.UserDao;
import com.mycompany.managelibrary.view.BookView;
import com.mycompany.managelibrary.view.LoanReturnView;
import com.mycompany.managelibrary.view.LoginView;

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
        bookView.setVisible(true); // Hiển thị giao diện sách
        loginView.dispose(); // Đóng giao diện đăng nhập
    }

    class LoginListener implements LoginView.LoginListener {
        public void onLogin() {
            User user = loginView.getUser();
            if (userDao.checkUser(user)) {
                showBookView();
            } else {
                loginView.showMessage("Tên đăng nhập hoặc mật khẩu không chính xác!");
            }
        }
    }
}
