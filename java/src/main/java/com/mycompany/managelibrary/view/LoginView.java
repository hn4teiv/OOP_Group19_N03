package com.mycompany.managelibrary.view;

import com.mycompany.managelibrary.controller.LoginController;
import com.mycompany.managelibrary.dao.UserDao;
import com.mycompany.managelibrary.entity.User;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView extends Application {
    private TextField userNameField;
    private PasswordField passwordField;
    private VBox layout;
    private Button loginBtn, registerBtn, cancelBtn;  // Thêm nút đăng ký và hủy
    private UserDao userDao;  // Đối tượng UserDao để kiểm tra và lưu người dùng

    @Override
    public void start(Stage stage) {
        // Khởi tạo các trường và nút đăng nhập, đăng ký, và hủy
        this.userNameField = new TextField();
        this.passwordField = new PasswordField();
        this.loginBtn = new Button("Login");
        this.registerBtn = new Button("Register");
        this.cancelBtn = new Button("Cancel");

        // Tạo đối tượng UserDao để thao tác với dữ liệu người dùng
        userDao = new UserDao();

        // Tạo layout và các thành phần giao diện
        Label userNameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");

        // Layout
        layout = new VBox(10, userNameLabel, userNameField, passwordLabel, passwordField, loginBtn, registerBtn, cancelBtn);
        stage.setScene(new Scene(layout, 300, 250));
        stage.setTitle("Login");

        // Khởi tạo controller với LoginView và Stage
        LoginController loginController = new LoginController(this, stage);

        // Đăng ký sự kiện cho các nút
        addLoginListener(loginController);
        addRegisterListener();
        addCancelListener(stage);

        // Hiển thị cửa sổ đăng nhập
        stage.show();
    }

    // Thêm sự kiện cho nút đăng nhập
    public void addLoginListener(LoginController controller) {
        loginBtn.setOnAction(e -> controller.handleLogin());  // Gọi phương thức handleLogin từ LoginController
    }

    // Thêm sự kiện cho nút đăng ký
    private void addRegisterListener() {
        registerBtn.setOnAction(e -> {
            User user = getUser();
            if (userDao.registerUser(user)) {
                showMessage("Đăng ký thành công!");
                clearFields();
            } else {
                showMessage("Đăng ký thất bại! Tên đăng nhập đã tồn tại.");
            }
        });
    }

    // Thêm sự kiện cho nút hủy
    private void addCancelListener(Stage stage) {
        cancelBtn.setOnAction(e -> stage.close());  // Đóng ứng dụng
    }

    // Phương thức hiển thị thông báo
    public void showMessage(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Lấy thông tin người dùng từ các trường nhập
    public User getUser() {
        return new User(userNameField.getText(), passwordField.getText());
    }

    // Xóa các trường nhập
    public void clearFields() {
        userNameField.clear();
        passwordField.clear();
    }

    public static void main(String[] args) {
        launch(args);  // Chạy ứng dụng JavaFX
    }
}
