package com.mycompany.managelibrary.view;

import java.util.List;

import com.mycompany.managelibrary.entity.LoanReturn;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoanReturnView {
    private Stage loanReturnStage;
    private GridPane layout;
    private VBox buttonLayout;

    // Các thành phần của giao diện
    private Label idLabel;
    private TextField idField;
    private Label diaChiLabel;
    private TextField diaChiField;
    private Label tenLabel;
    private TextField tenField;
    private Label lopLabel;
    private TextField lopField;
    private Label tenSachLabel;
    private TextField tenSachField;
    private Label trangThaiLabel;
    private TextField trangThaiField;

    private Button addButton;
    private Button editButton;
    private Button deleteButton;
    private Button searchButton;
    private Button switchToBookButton;

    // Thêm phần hiển thị danh sách LoanReturn
    private ListView<String> loanReturnListView;

    public LoanReturnView() {
        // Khởi tạo các thành phần giao diện
        layout = new GridPane();
        buttonLayout = new VBox(10);

        idLabel = new Label("ID:");
        idField = new TextField();
        diaChiLabel = new Label("Địa chỉ:");
        diaChiField = new TextField();
        tenLabel = new Label("Tên:");
        tenField = new TextField();
        lopLabel = new Label("Lớp:");
        lopField = new TextField();
        tenSachLabel = new Label("Tên sách:");
        tenSachField = new TextField();
        trangThaiLabel = new Label("Trạng thái:");
        trangThaiField = new TextField();

        addButton = new Button("Thêm");
        editButton = new Button("Cập nhật");
        deleteButton = new Button("Xóa");
        searchButton = new Button("Tìm kiếm");
        switchToBookButton = new Button("Chuyển sang Quản lý Sách");

        // Danh sách LoanReturn
        loanReturnListView = new ListView<>();
        ObservableList<String> loanReturnItems = FXCollections.observableArrayList();
        loanReturnListView.setItems(loanReturnItems);

        // Cấu hình layout
        layout.add(idLabel, 0, 0);
        layout.add(idField, 1, 0);
        layout.add(diaChiLabel, 0, 1);
        layout.add(diaChiField, 1, 1);
        layout.add(tenLabel, 0, 2);
        layout.add(tenField, 1, 2);
        layout.add(lopLabel, 0, 3);
        layout.add(lopField, 1, 3);
        layout.add(tenSachLabel, 0, 4);
        layout.add(tenSachField, 1, 4);
        layout.add(trangThaiLabel, 0, 5);
        layout.add(trangThaiField, 1, 5);

        buttonLayout.getChildren().addAll(addButton, editButton, deleteButton, searchButton, switchToBookButton);

        layout.add(buttonLayout, 0, 6, 2, 1);
        layout.add(loanReturnListView, 0, 7, 2, 1);

        loanReturnStage = new Stage();
        loanReturnStage.setTitle("Quản lý Mượn Trả");
    }

    // Hàm để hiển thị LoanReturnView
    public GridPane getView() {
        return layout;  // Trả về layout thay vì Scene
    }

    // Phương thức để hiển thị danh sách mượn trả
    public void showListLoanReturn(List<LoanReturn> loanReturnList) {
        ObservableList<String> loanReturnItems = FXCollections.observableArrayList();
        for (LoanReturn loanReturn : loanReturnList) {
            loanReturnItems.add("ID: " + loanReturn.getId() + " - " + loanReturn.getTen() + " - " + loanReturn.getTrangThai());
        }
        loanReturnListView.setItems(loanReturnItems);
    }

    // Lấy thông tin mượn trả từ các trường
    public LoanReturn getLoanReturnInfo() {
        try {
            int id = Integer.parseInt(idField.getText());
            String diaChi = diaChiField.getText();
            String ten = tenField.getText();
            String lop = lopField.getText();
            String tenSach = tenSachField.getText();
            String trangThai = trangThaiField.getText();

            return new LoanReturn(id, diaChi, ten, lop, tenSach, trangThai);
        } catch (NumberFormatException e) {
            return null; // Trả về null nếu thông tin nhập vào không hợp lệ
        }
    }

    // Hiển thị thông báo
    public void showMessage(String message) {
        // Cách đơn giản hiển thị thông báo (chưa có mã chi tiết)
        System.out.println(message);
    }

    // Thêm listener cho các button
    public void addAddLoanReturnListener(EventHandler<ActionEvent> listener) {
        addButton.setOnAction(listener);
    }

    public void addEditLoanReturnListener(EventHandler<ActionEvent> listener) {
        editButton.setOnAction(listener);
    }

    public void addDeleteLoanReturnListener(EventHandler<ActionEvent> listener) {
        deleteButton.setOnAction(listener);
    }

    public void addSearchListener(EventHandler<ActionEvent> listener) {
        searchButton.setOnAction(listener);
    }

    public void addSwitchToBookViewListener(EventHandler<ActionEvent> listener) {
        switchToBookButton.setOnAction(listener);
    }

    // Reset form và thông tin
    public void clearLoanReturnInfo() {
        idField.clear();
        diaChiField.clear();
        tenField.clear();
        lopField.clear();
        tenSachField.clear();
        trangThaiField.clear();
    }

    public void resetButtons() {
        addButton.setDisable(false);
        editButton.setDisable(false);
        deleteButton.setDisable(false);
        searchButton.setDisable(false);
    }
}
