package com.mycompany.managelibrary.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LoanReturnView {
    private TableView<LoanReturn> table;
    private TextField idField;
    private TextField diaChiField;
    private TextField tenSachField;
    private TextField lopField;
    private TextField trangThaiField;
    private Button addButton;
    private Button editButton;
    private Button deleteButton;

    private ObservableList<LoanReturn> loanReturnList;

    public LoanReturnView(Stage primaryStage) {
        primaryStage.setTitle("Loan Return Management");

        loanReturnList = FXCollections.observableArrayList();

        table = new TableView<>();
        idField = new TextField();
        diaChiField = new TextField();
        tenSachField = new TextField();
        lopField = new TextField();
        trangThaiField = new TextField();
        addButton = new Button("Add LoanReturn");
        editButton = new Button("Edit LoanReturn");
        deleteButton = new Button("Delete LoanReturn");

        TableColumn<LoanReturn, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        TableColumn<LoanReturn, String> diaChiColumn = new TableColumn<>("Address");
        diaChiColumn.setCellValueFactory(cellData -> cellData.getValue().diaChiProperty());

        TableColumn<LoanReturn, String> tenSachColumn = new TableColumn<>("Book Title");
        tenSachColumn.setCellValueFactory(cellData -> cellData.getValue().tenSachProperty());

        TableColumn<LoanReturn, String> lopColumn = new TableColumn<>("Class");
        lopColumn.setCellValueFactory(cellData -> cellData.getValue().lopProperty());

        TableColumn<LoanReturn, String> trangThaiColumn = new TableColumn<>("Status");
        trangThaiColumn.setCellValueFactory(cellData -> cellData.getValue().trangThaiProperty());

        table.getColumns().addAll(idColumn, diaChiColumn, tenSachColumn, lopColumn, trangThaiColumn);

        table.setItems(loanReturnList);

        GridPane form = new GridPane();
        form.setPadding(new Insets(10));
        form.setVgap(8);
        form.setHgap(10);

        form.add(new Label("ID:"), 0, 0);
        form.add(idField, 1, 0);
        form.add(new Label("Address:"), 0, 1);
        form.add(diaChiField, 1, 1);
        form.add(new Label("Book Title:"), 0, 2);
        form.add(tenSachField, 1, 2);
        form.add(new Label("Class:"), 0, 3);
        form.add(lopField, 1, 3);
        form.add(new Label("Status:"), 0, 4);
        form.add(trangThaiField, 1, 4);

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(addButton, editButton, deleteButton);

        BorderPane layout = new BorderPane();
        layout.setCenter(table);
        layout.setBottom(form);
        layout.setRight(buttonBox);

        Scene scene = new Scene(layout, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        addButton.setOnAction(e -> addLoanReturn());
        editButton.setOnAction(e -> editLoanReturn());
        deleteButton.setOnAction(e -> deleteLoanReturn());
    }

    private void addLoanReturn() {
        LoanReturn loanReturn = new LoanReturn(
                Integer.parseInt(idField.getText()),
                diaChiField.getText(),
                tenSachField.getText(),
                lopField.getText(),
                trangThaiField.getText()
        );
        loanReturnList.add(loanReturn);
        clearForm();
    }

    private void editLoanReturn() {
        LoanReturn selectedLoanReturn = table.getSelectionModel().getSelectedItem();
        if (selectedLoanReturn != null) {
            selectedLoanReturn.setId(Integer.parseInt(idField.getText()));
            selectedLoanReturn.setDiaChi(diaChiField.getText());
            selectedLoanReturn.setTenSach(tenSachField.getText());
            selectedLoanReturn.setLop(lopField.getText());
            selectedLoanReturn.setTrangThai(trangThaiField.getText());
            table.refresh();
            clearForm();
        }
    }

    private void deleteLoanReturn() {
        LoanReturn selectedLoanReturn = table.getSelectionModel().getSelectedItem();
        if (selectedLoanReturn != null) {
            loanReturnList.remove(selectedLoanReturn);
            clearForm();
        }
    }

    private void clearForm() {
        idField.clear();
        diaChiField.clear();
        tenSachField.clear();
        lopField.clear();
        trangThaiField.clear();
    }

    public void setLoanReturnForm(LoanReturn loanReturn) {
        idField.setText(String.valueOf(loanReturn.getId()));
        diaChiField.setText(loanReturn.getDiaChi());
        tenSachField.setText(loanReturn.getTenSach());
        lopField.setText(loanReturn.getLop());
        trangThaiField.setText(loanReturn.getTrangThai());
    }
}
