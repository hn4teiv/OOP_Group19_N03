package com.mycompany.managelibrary.view;

import com.mycompany.managelibrary.entity.LoanReturn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class LoanReturnView {
    private TableView<LoanReturn> loanReturnTable;
    private TextField diaChiField;
    private TextField tenField;
    private TextField lopField;
    private TextField tenSachField;
    private TextField trangThaiField;
    private Button addButton;
    private Button editButton;
    private Button deleteButton;

    public LoanReturnView() {
        loanReturnTable = new TableView<>();
        initializeUI();
    }

    private void initializeUI() {
        TableColumn<LoanReturn, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        TableColumn<LoanReturn, String> diaChiColumn = new TableColumn<>("Địa Chỉ");
        diaChiColumn.setCellValueFactory(cellData -> cellData.getValue().diaChiProperty());

        TableColumn<LoanReturn, String> tenColumn = new TableColumn<>("Tên");
        tenColumn.setCellValueFactory(cellData -> cellData.getValue().tenProperty());

        TableColumn<LoanReturn, String> lopColumn = new TableColumn<>("Lớp");
        lopColumn.setCellValueFactory(cellData -> cellData.getValue().lopProperty());

        TableColumn<LoanReturn, String> tenSachColumn = new TableColumn<>("Tên Sách");
        tenSachColumn.setCellValueFactory(cellData -> cellData.getValue().tenSachProperty());

        TableColumn<LoanReturn, String> trangThaiColumn = new TableColumn<>("Trạng Thái");
        trangThaiColumn.setCellValueFactory(cellData -> cellData.getValue().trangThaiProperty());

        loanReturnTable.getColumns().addAll(idColumn, diaChiColumn, tenColumn, lopColumn, tenSachColumn, trangThaiColumn);

        diaChiField = new TextField();
        tenField = new TextField();
        lopField = new TextField();
        tenSachField = new TextField();
        trangThaiField = new TextField();

        addButton = new Button("Thêm");
        editButton = new Button("Sửa");
        deleteButton = new Button("Xóa");

        addButton.setOnAction(event -> addLoanReturn());
        editButton.setOnAction(event -> editLoanReturn());
        deleteButton.setOnAction(event -> deleteLoanReturn());

        VBox inputLayout = new VBox(10, new Label("Địa Chỉ"), diaChiField, new Label("Tên"), tenField,
                new Label("Lớp"), lopField, new Label("Tên Sách"), tenSachField,
                new Label("Trạng Thái"), trangThaiField, addButton, editButton, deleteButton);
        inputLayout.setPadding(new Insets(10));

        BorderPane mainLayout = new BorderPane();
        mainLayout.setCenter(loanReturnTable);
        mainLayout.setRight(inputLayout);

        Scene scene = new Scene(mainLayout, 800, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Quản lý Mượn Trả");
        stage.show();
    }

    public void setLoanReturnList(List<LoanReturn> loanReturns) {
        ObservableList<LoanReturn> observableList = FXCollections.observableArrayList(loanReturns);
        loanReturnTable.setItems(observableList);
    }

    public LoanReturn getLoanReturnFromInput() {
        LoanReturn loanReturn = new LoanReturn();
        loanReturn.setDiaChi(diaChiField.getText());
        loanReturn.setTen(tenField.getText());
        loanReturn.setLop(lopField.getText());
        loanReturn.setTenSach(tenSachField.getText());
        loanReturn.setTrangThai(trangThaiField.getText());
        return loanReturn;
    }

    public LoanReturn getSelectedLoanReturn() {
        return loanReturnTable.getSelectionModel().getSelectedItem();
    }

    public int getSelectedLoanReturnId() {
        LoanReturn selectedLoanReturn = getSelectedLoanReturn();
        return selectedLoanReturn != null ? selectedLoanReturn.getId() : -1;
    }

    private void addLoanReturn() {
        // Được xử lý trong LoanReturnController
    }

    private void editLoanReturn() {
        // Được xử lý trong LoanReturnController
    }

    private void deleteLoanReturn() {
        // Được xử lý trong LoanReturnController
    }

    public void addAddLoanReturnListener(EventHandler<ActionEvent> listener) {
        addButton.setOnAction(listener);
    }

    public void addEditLoanReturnListener(EventHandler<ActionEvent> listener) {
        editButton.setOnAction(listener);
    }

    public void addDeleteLoanReturnListener(EventHandler<ActionEvent> listener) {
        deleteButton.setOnAction(listener);
    }

    public BorderPane getView() {
        return (BorderPane) loanReturnTable.getParent();
    }
}
