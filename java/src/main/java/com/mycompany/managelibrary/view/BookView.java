package com.mycompany.managelibrary.view;

import java.util.List;

import com.mycompany.managelibrary.dao.BookDao;
import com.mycompany.managelibrary.entity.Book;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookView extends Application {
    private TableView<Book> bookTable;
    private TextField tenBookField, loaiBookField, giaThanhField, soLuongField, maSoField, nhaXuatBanField, tacGiaField;
    private Button addBookBtn, editBookBtn, deleteBookBtn, switchToLoanReturnViewBtn;
    private ObservableList<Book> bookList;
    private BookDao bookDao;

    public BookView() {
        bookList = FXCollections.observableArrayList();
        bookDao = new BookDao();
    }

    // Thêm phương thức này vào để trả về layout
    public VBox getLayout() {
        return createLayout();  // Sử dụng createLayout() để trả về layout đã tạo
    }

    // Phương thức này trả về Scene sử dụng layout đã được thiết lập
    public Parent getView() {
        return createLayout(); // Trả về layout (Parent) thay vì Scene
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Quản lý Sách");

        bookTable = new TableView<>();
        setupTable();

        initializeComponents();
        // Sử dụng createLayout() thay vì setLayouts()
        Scene scene = new Scene(createLayout(), 800, 600);

        showListBook(bookDao.getListBooks());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Hàm khởi tạo các thành phần UI (TextFields, Buttons, etc.)
    private void initializeComponents() {
        tenBookField = new TextField();
        loaiBookField = new TextField();
        giaThanhField = new TextField();
        soLuongField = new TextField();
        maSoField = new TextField();
        nhaXuatBanField = new TextField();
        tacGiaField = new TextField();

        addBookBtn = new Button("Thêm");
        addBookBtn.setOnAction(e -> addBook());

        editBookBtn = new Button("Sửa");
        editBookBtn.setOnAction(e -> editBook());

        deleteBookBtn = new Button("Xóa");
        deleteBookBtn.setOnAction(e -> deleteBook());

        // Nút chuyển sang giao diện Quản lý Mượn Trả
        switchToLoanReturnViewBtn = new Button("Chuyển đến Quản lý Mượn Trả");
        switchToLoanReturnViewBtn.setOnAction(e -> switchToLoanReturnView());  // Sự kiện chuyển giao diện
    }

    // Hàm thiết lập các cột trong TableView
    private void setupTable() {
        TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Book, String> tenBookColumn = new TableColumn<>("Tên Sách");
        tenBookColumn.setCellValueFactory(new PropertyValueFactory<>("tenBook"));

        TableColumn<Book, String> loaiBookColumn = new TableColumn<>("Loại Sách");
        loaiBookColumn.setCellValueFactory(new PropertyValueFactory<>("loaiBook"));

        TableColumn<Book, Double> giaThanhColumn = new TableColumn<>("Giá Thành");
        giaThanhColumn.setCellValueFactory(new PropertyValueFactory<>("giaThanh"));

        TableColumn<Book, Integer> soLuongColumn = new TableColumn<>("Số Lượng");
        soLuongColumn.setCellValueFactory(new PropertyValueFactory<>("soLuong"));

        TableColumn<Book, String> maSoColumn = new TableColumn<>("Mã Số");
        maSoColumn.setCellValueFactory(new PropertyValueFactory<>("maSo"));

        TableColumn<Book, String> nhaXuatBanColumn = new TableColumn<>("Nhà Xuất Bản");
        nhaXuatBanColumn.setCellValueFactory(new PropertyValueFactory<>("nhaXuatBan"));

        TableColumn<Book, String> tacGiaColumn = new TableColumn<>("Tác Giả");
        tacGiaColumn.setCellValueFactory(new PropertyValueFactory<>("tacGia"));

        bookTable.getColumns().addAll(idColumn, tenBookColumn, loaiBookColumn, giaThanhColumn, soLuongColumn, maSoColumn, nhaXuatBanColumn, tacGiaColumn);
        bookTable.setItems(bookList);
    }

    // Hàm thiết lập giao diện (Layout) với các thành phần
    private VBox createLayout() {
        GridPane formPanel = new GridPane();
        formPanel.setHgap(10);
        formPanel.setVgap(10);
        formPanel.add(new Label("Tên Sách:"), 0, 0);
        formPanel.add(tenBookField, 1, 0);
        formPanel.add(new Label("Loại Sách:"), 0, 1);
        formPanel.add(loaiBookField, 1, 1);
        formPanel.add(new Label("Giá Thành:"), 0, 2);
        formPanel.add(giaThanhField, 1, 2);
        formPanel.add(new Label("Số Lượng:"), 0, 3);
        formPanel.add(soLuongField, 1, 3);
        formPanel.add(new Label("Mã Số:"), 0, 4);
        formPanel.add(maSoField, 1, 4);
        formPanel.add(new Label("Nhà Xuất Bản:"), 0, 5);
        formPanel.add(nhaXuatBanField, 1, 5);
        formPanel.add(new Label("Tác Giả:"), 0, 6);
        formPanel.add(tacGiaField, 1, 6);

        VBox buttonPanel = new VBox(10, addBookBtn, editBookBtn, deleteBookBtn, switchToLoanReturnViewBtn);

        BorderPane layout = new BorderPane();
        layout.setLeft(formPanel);
        layout.setCenter(bookTable);
        layout.setBottom(buttonPanel);

        return new VBox(layout);
    }

    // Hiển thị danh sách sách lên TableView
    public void showListBook(List<Book> books) {
        bookList.clear();
        bookList.addAll(books);
    }

    // Xóa thông tin sách trong các ô nhập liệu
    public void clearBookInfo() {
        tenBookField.clear();
        loaiBookField.clear();
        giaThanhField.clear();
        soLuongField.clear();
        maSoField.clear();
        nhaXuatBanField.clear();
        tacGiaField.clear();
    }

    // Hàm thêm sách
    private void addBook() {
        Book book = getBookInfo();
        bookDao.add(book);
        showListBook(bookDao.getListBooks());
        clearBookInfo();
        showMessage("Thêm sách thành công!");
    }

    // Hàm sửa sách
    private void editBook() {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            selectedBook.setTenBook(tenBookField.getText());
            selectedBook.setLoaiBook(loaiBookField.getText());
            selectedBook.setGiaThanh(Double.parseDouble(giaThanhField.getText()));
            selectedBook.setSoLuong(Integer.parseInt(soLuongField.getText()));
            selectedBook.setMaSo(maSoField.getText());
            selectedBook.setNhaXuatBan(nhaXuatBanField.getText());
            selectedBook.setTacGia(tacGiaField.getText());

            bookDao.edit(selectedBook);
            showListBook(bookDao.getListBooks());
            clearBookInfo();
            showMessage("Sửa sách thành công!");
        }
    }

    // Hàm xóa sách
    private void deleteBook() {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            bookDao.delete(selectedBook.getId());
            showListBook(bookDao.getListBooks());
            clearBookInfo();
            showMessage("Xóa sách thành công!");
        }
    }

    // Hiển thị thông báo cho người dùng
    public void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Lấy thông tin sách từ các TextField
    public Book getBookInfo() {
        Book book = new Book();
        book.setTenBook(tenBookField.getText());
        book.setLoaiBook(loaiBookField.getText());
        book.setGiaThanh(Double.parseDouble(giaThanhField.getText()));
        book.setSoLuong(Integer.parseInt(soLuongField.getText()));
        book.setMaSo(maSoField.getText());
        book.setNhaXuatBan(nhaXuatBanField.getText());
        book.setTacGia(tacGiaField.getText());
        return book;
    }

    // Hàm main để chạy ứng dụng
    public static void main(String[] args) {
        launch(args);
    }
    public void addAddBookListener(EventHandler<ActionEvent> handler) {
        addBookBtn.setOnAction(handler);
    }

    public void addEditBookListener(EventHandler<ActionEvent> handler) {
        editBookBtn.setOnAction(handler);
    }

    public void addDeleteBookListener(EventHandler<ActionEvent> handler) {
        deleteBookBtn.setOnAction(handler);
    }
    public void addSwitchToLoanReturnViewListener(EventHandler<ActionEvent> handler) {
        switchToLoanReturnViewBtn.setOnAction(handler);
    }

    // Chuyển sang giao diện LoanReturnView
    private void switchToLoanReturnView() {
        LoanReturnView loanReturnView = new LoanReturnView();
        Scene loanReturnScene = new Scene(loanReturnView.getView(), 800, 600);

        // Lấy Stage hiện tại từ nút chuyển
        Stage currentStage = (Stage) switchToLoanReturnViewBtn.getScene().getWindow();
        currentStage.setScene(loanReturnScene);
    }

}
