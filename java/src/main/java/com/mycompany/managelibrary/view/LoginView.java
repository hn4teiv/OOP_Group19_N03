package com.mycompany.managelibrary.view;

import com.mycompany.managelibrary.controller.LoginController;
import com.mycompany.managelibrary.dao.UserDao;
import com.mycompany.managelibrary.entity.User;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginView extends Application {
    private TextField userNameField;
    private PasswordField passwordField;
    private Pane layout;
    private Button loginBtn, registerBtn, cancelBtn;
    private UserDao userDao;

    @Override
    public void start(Stage stage) {
        try {
            // Tải giao diện từ file FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login_view.fxml"));

            // Sử dụng biến fxmlLoader để tải layout
            layout = fxmlLoader.load();

            // Lấy các thành phần từ file FXML
            userNameField = (TextField) layout.lookup("#userNameField");
            passwordField = (PasswordField) layout.lookup("#passwordField");
            loginBtn = (Button) layout.lookup("#loginBtn");
            registerBtn = (Button) layout.lookup("#registerBtn");
            cancelBtn = (Button) layout.lookup("#cancelBtn");

            userDao = new UserDao();

            // Khởi tạo controller
            LoginController loginController = new LoginController(this, stage);

            // Đăng ký sự kiện cho các nút
            addLoginListener(loginController);
            addRegisterListener();
            addCancelListener(stage);

            // Hiển thị cửa sổ
            stage.setScene(new Scene(layout));
            stage.setTitle("Login");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showMessage("Lỗi khi tải giao diện!");
        }
    }


    public void addLoginListener(LoginController controller) {
        loginBtn.setOnAction(e -> controller.handleLogin());
    }

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

    private void addCancelListener(Stage stage) {
        cancelBtn.setOnAction(e -> stage.close());
    }

    public void showMessage(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public User getUser() {
        return new User(userNameField.getText(), passwordField.getText());
    }

    public void clearFields() {
        userNameField.clear();
        passwordField.clear();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
