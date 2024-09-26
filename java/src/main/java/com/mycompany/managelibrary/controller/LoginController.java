package com.mycompany.managelibrary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    }

    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if (userDao.checkUser(user)) {
                showBookView();
            } else {
                loginView.showMessage("Invalid user credentials");
            }
        }
    }
}
