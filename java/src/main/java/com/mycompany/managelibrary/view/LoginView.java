package com.mycompany.managelibrary.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;

    public LoginView() {
        // Thiết lập tiêu đề cho cửa sổ
        setTitle("Đăng Nhập");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Đặt vị trí cửa sổ ở giữa màn hình

        // Tạo các thành phần giao diện
        JLabel usernameLabel = new JLabel("Tên đăng nhập:");
        usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Mật khẩu:");
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Đăng Nhập");
        cancelButton = new JButton("Hủy");

        // Tạo panel và thêm các thành phần vào
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2)); // Thiết lập layout dạng lưới
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(cancelButton);

        // Thêm panel vào frame
        add(panel);

        // Xử lý sự kiện cho nút đăng nhập
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic xử lý đăng nhập ở đây
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                System.out.println("Tên đăng nhập: " + username);
                System.out.println("Mật khẩu: " + password);
                // Thêm mã kiểm tra thông tin đăng nhập ở đây
            }
        });

        // Xử lý sự kiện cho nút hủy
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Đóng ứng dụng khi bấm nút hủy
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        // Hiển thị giao diện đăng nhập
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }
}
