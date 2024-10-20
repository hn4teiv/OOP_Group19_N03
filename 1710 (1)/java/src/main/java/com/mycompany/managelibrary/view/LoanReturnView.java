package com.mycompany.managelibrary.view;

import java.util.List;

import com.mycompany.managelibrary.dao.BookDao;
import com.mycompany.managelibrary.dao.LoanReturnDao;
import com.mycompany.managelibrary.entity.Book;
import com.mycompany.managelibrary.entity.LoanReturn;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

public class LoanReturnView extends Application {
    private TableView<LoanReturn> loanReturnTable;
    private TextField diaChiField, tenField, lopField, tenSachField, trangThaiField;
    private Button addLoanReturnBtn, editLoanReturnBtn, deleteLoanReturnBtn, switchToBookViewBtn;
    private ObservableList<LoanReturn> loanReturnList;
    private LoanReturnDao loanReturnDao;
    private ComboBox<String> trangThaiComboBox;
    private ComboBox<String> tenSachComboBox; // Thêm ComboBox cho tên sách
    private BookDao bookDao; // Thêm biến BookDao


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Quản lý Mượn Trả");

        loanReturnTable = new TableView<>();
        loanReturnList = FXCollections.observableArrayList();
        loanReturnDao = new LoanReturnDao();
        bookDao = new BookDao();

        setupTable();
        initializeComponents();
        VBox layout = createLayout();

        Scene scene = new Scene(layout, 800, 600);
        showListLoanReturn(loanReturnDao.getListLoanReturns());

        // Ràng buộc sự kiện cho nút chuyển đổi
        switchToBookViewBtn.setOnAction(event -> switchToBookView(primaryStage));

        // Thêm sự kiện chọn cho bảng loanReturnTable
        loanReturnTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                fillFieldsWithLoanReturnInfo(newValue); // Gọi phương thức để điền thông tin vào các field
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Phương thức để điền thông tin vào các field từ đối tượng LoanReturn đã chọn
    private void fillFieldsWithLoanReturnInfo(LoanReturn loanReturn) {
        diaChiField.setText(loanReturn.getDiaChi());
        tenField.setText(loanReturn.getTen());
        lopField.setText(loanReturn.getLop());
        tenSachComboBox.setValue(loanReturn.getTenSach()); // Điền tên sách vào ComboBox
        trangThaiComboBox.setValue(loanReturn.getTrangThai()); // Điền trạng thái vào ComboBox
    }

    private void initializeComponents() {
        diaChiField = new TextField();
        tenField = new TextField();
        lopField = new TextField();
        tenSachField = new TextField();

        // Tạo ComboBox cho trạng thái
        tenSachComboBox = new ComboBox<>();

        trangThaiComboBox = new ComboBox<>();
        trangThaiComboBox.getItems().addAll("Đang mượn", "Đã trả"); // Các trạng thái có sẵn

        addLoanReturnBtn = new Button("Thêm");
        editLoanReturnBtn = new Button("Sửa");
        deleteLoanReturnBtn = new Button("Xóa");
        switchToBookViewBtn = new Button("Chuyển đến Quản lý Sách");

        addLoanReturnBtn.setOnAction(event -> addLoanReturn());
        editLoanReturnBtn.setOnAction(event -> editLoanReturn());
        deleteLoanReturnBtn.setOnAction(event -> deleteLoanReturn());
    }
    private void setupTable() {
        TableColumn<LoanReturn, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<LoanReturn, String> diaChiColumn = new TableColumn<>("Địa Chỉ");
        diaChiColumn.setCellValueFactory(new PropertyValueFactory<>("diaChi"));

        TableColumn<LoanReturn, String> tenColumn = new TableColumn<>("Tên");
        tenColumn.setCellValueFactory(new PropertyValueFactory<>("ten"));

        TableColumn<LoanReturn, String> lopColumn = new TableColumn<>("Lớp");
        lopColumn.setCellValueFactory(new PropertyValueFactory<>("lop"));

        TableColumn<LoanReturn, String> tenSachColumn = new TableColumn<>("Tên Sách");
        tenSachColumn.setCellValueFactory(new PropertyValueFactory<>("tenSach"));

        TableColumn<LoanReturn, String> trangThaiColumn = new TableColumn<>("Trạng Thái");
        trangThaiColumn.setCellValueFactory(new PropertyValueFactory<>("trangThai"));

        loanReturnTable.getColumns().addAll(idColumn, diaChiColumn, tenColumn, lopColumn, tenSachColumn, trangThaiColumn);
        loanReturnTable.setItems(loanReturnList);
    }

    public VBox createLayout() {
        GridPane formPanel = new GridPane();
        formPanel.setHgap(10);
        formPanel.setVgap(10);
        formPanel.add(new Label("Địa Chỉ:"), 0, 0);
        formPanel.add(diaChiField, 1, 0);
        formPanel.add(new Label("Tên:"), 0, 1);
        formPanel.add(tenField, 1, 1);
        formPanel.add(new Label("Lớp:"), 0, 2);
        formPanel.add(lopField, 1, 2);
        formPanel.add(new Label("Tên Sách:"), 0, 3);
        formPanel.add(tenSachComboBox, 1, 3); // Sử dụng ComboBox thay vì TextField
        formPanel.add(new Label("Trạng Thái:"), 0, 4);
        formPanel.add(trangThaiComboBox, 1, 4); // Thêm ComboBox vào layout

        VBox buttonPanel = new VBox(10, addLoanReturnBtn, editLoanReturnBtn, deleteLoanReturnBtn, switchToBookViewBtn);

        BorderPane layout = new BorderPane();
        layout.setLeft(formPanel);
        layout.setCenter(loanReturnTable);
        layout.setBottom(buttonPanel);

        return new VBox(layout);
    }
    public void showListLoanReturn(List<LoanReturn> loanReturns) {
        loanReturnList.clear();
        loanReturnList.addAll(loanReturns);

        // Cập nhật ComboBox tên sách
        tenSachComboBox.getItems().clear();

        // Lấy danh sách sách từ BookDao
        ObservableList<Book> books = bookDao.getListBooks();
        for (Book book : books) {
            tenSachComboBox.getItems().add(book.getTenBook()); // Thêm tên sách vào ComboBox
        }
    }



    public LoanReturn getLoanReturnInfo() {
        LoanReturn loanReturn = new LoanReturn();
        loanReturn.setDiaChi(diaChiField.getText());
        loanReturn.setTen(tenField.getText());
        loanReturn.setLop(lopField.getText());
        loanReturn.setTenSach(tenSachComboBox.getValue()); // Lấy giá trị từ ComboBox tên sách
        loanReturn.setTrangThai(trangThaiComboBox.getValue());
        return loanReturn;
    }


    public void clearLoanReturnInfo() {
        diaChiField.clear();
        tenField.clear();
        lopField.clear();
        tenSachField.clear();
        trangThaiField.clear();
    }

    public void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void switchToBookView(Stage stage) {
        BookView bookView = new BookView();
        try {
            bookView.start(stage);
        } catch (Exception e) {
            showMessage("Lỗi khi chuyển sang giao diện Quản lý Sách: " + e.getMessage());
        }
    }

    // Thêm phương thức để thêm thông tin mượn trả
    private void addLoanReturn() {
        LoanReturn loanReturn = getLoanReturnInfo();
        loanReturnDao.add(loanReturn);
        showListLoanReturn(loanReturnDao.getListLoanReturns()); // Cập nhật danh sách
        clearLoanReturnInfo(); // Xóa thông tin nhập
        showMessage("Thêm mượn trả thành công!"); // Thông báo thành công
    }

    // Thêm phương thức để sửa thông tin mượn trả
    private void editLoanReturn() {
        LoanReturn selectedLoanReturn = loanReturnTable.getSelectionModel().getSelectedItem();
        if (selectedLoanReturn != null) {
            // Lấy thông tin từ các trường nhập liệu
            LoanReturn updatedLoanReturn = getLoanReturnInfo();
            updatedLoanReturn.setId(selectedLoanReturn.getId()); // Đảm bảo ID của đối tượng sửa là ID của đối tượng được chọn

            loanReturnDao.edit(updatedLoanReturn); // Gọi phương thức edit với đối tượng đã cập nhật
            showListLoanReturn(loanReturnDao.getListLoanReturns()); // Cập nhật danh sách
            clearLoanReturnInfo(); // Xóa thông tin nhập
            showMessage("Cập nhật mượn trả thành công!"); // Thông báo thành công
        } else {
            showMessage("Vui lòng chọn một mượn trả để sửa."); // Thông báo nếu không có lựa chọn
        }
    }


    // Thêm phương thức để xóa thông tin mượn trả
    private void deleteLoanReturn() {
        LoanReturn loanReturn = loanReturnTable.getSelectionModel().getSelectedItem();
        if (loanReturn != null) {
            loanReturnDao.delete(loanReturn.getId());
            showListLoanReturn(loanReturnDao.getListLoanReturns()); // Cập nhật danh sách
            clearLoanReturnInfo(); // Xóa thông tin nhập
            showMessage("Xóa mượn trả thành công!"); // Thông báo thành công
        } else {
            showMessage("Vui lòng chọn một mượn trả để xóa."); // Thông báo nếu không có lựa chọn
        }
    }

    public void addAddLoanReturnListener(EventHandler<ActionEvent> handler) {
        addLoanReturnBtn.setOnAction(handler);
    }

    public void addEditLoanReturnListener(EventHandler<ActionEvent> handler) {
        editLoanReturnBtn.setOnAction(handler);
    }

    public void addDeleteLoanReturnListener(EventHandler<ActionEvent> handler) {
        deleteLoanReturnBtn.setOnAction(handler);
    }

    public void addSwitchToBookViewListener(EventHandler<ActionEvent> handler) {
        switchToBookViewBtn.setOnAction(handler);
    }
}