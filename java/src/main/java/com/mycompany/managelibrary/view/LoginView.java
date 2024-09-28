package com.mycompany.managelibrary.view;

import com.mycompany.managelibrary.dao.UserDao;
import com.mycompany.managelibrary.entity.User;
import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    private JButton registerButton;

    private UserDao userDao;
    private LoginListener loginListener; // Khai báo biến loginListener

    public LoginView() {
        setTitle("Đăng Nhập");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        userDao = new UserDao();

        JLabel usernameLabel = new JLabel("Tên đăng nhập:");
        usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Mật khẩu:");
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Đăng Nhập");
        cancelButton = new JButton("Hủy");
        registerButton = new JButton("Đăng Ký");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);
        panel.add(cancelButton);

        add(panel);

        cancelButton.addActionListener(e -> System.exit(0));

        loginButton.addActionListener(e -> {
            if (loginListener != null) {
                loginListener.onLogin(); // Gọi phương thức onLogin nếu loginListener không null
            }
        });

        registerButton.addActionListener(e -> {
            User user = getUser();
            if (userDao.registerUser(user)) {
                showMessage("Đăng ký thành công! Bạn có thể đăng nhập.");
            } else {
                showMessage("Tên đăng nhập đã tồn tại. Vui lòng chọn tên khác.");
            }
        });
    }

    public User getUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        return new User(username, password);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void addLoginListener(LoginListener listener) {
        this.loginListener = listener; // Gán loginListener
    }

    public static void main(String[] args) {
        LoginView loginView = new LoginView();

        loginView.addLoginListener(() -> {
            User user = loginView.getUser();
            UserDao userDao = new UserDao();
            if (userDao.checkUser(user)) {
                loginView.showMessage("Đăng nhập thành công!");
                // Mở BookView khi đăng nhập thành công
                BookView bookView = new BookView();
                bookView.setVisible(true);
                loginView.dispose(); // Đóng cửa sổ đăng nhập
            } else {
                loginView.showMessage("Đăng nhập thất bại!");
            }
        });

        SwingUtilities.invokeLater(() -> loginView.setVisible(true));
    }

    public interface LoginListener {
        void onLogin();
    }
}
