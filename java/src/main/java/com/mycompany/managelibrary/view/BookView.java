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

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Quản lý Sách");

        bookTable = new TableView<>();
        setupTable();

        initializeComponents();
        Scene scene = new Scene(createLayout(), 800, 600);

        // Hiển thị danh sách sách từ BookDao
        showListBook(bookDao.getListBooks());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

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

        switchToLoanReturnViewBtn = new Button("Chuyển đến Quản lý Mượn Trả");
        switchToLoanReturnViewBtn.setOnAction(e -> switchToLoanReturnView());

        // Thêm sự kiện chọn hàng trong bảng
        bookTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
                if (selectedBook != null) {
                    loadBookData(selectedBook);
                }
            }
        });
    }

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

    public VBox createLayout() {
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

    public void showListBook(List<Book> books) {
        bookList.clear();
        bookList.addAll(books);
    }

    public void clearBookInfo() {
        tenBookField.clear();
        loaiBookField.clear();
        giaThanhField.clear();
        soLuongField.clear();
        maSoField.clear();
        nhaXuatBanField.clear();
        tacGiaField.clear();
    }

    private void loadBookData(Book book) {
        tenBookField.setText(book.getTenBook());
        loaiBookField.setText(book.getLoaiBook());
        giaThanhField.setText(String.valueOf(book.getGiaThanh()));
        soLuongField.setText(String.valueOf(book.getSoLuong()));
        maSoField.setText(book.getMaSo());
        nhaXuatBanField.setText(book.getNhaXuatBan());
        tacGiaField.setText(book.getTacGia());
    }

    private void addBook() {
        try {
            Book book = getBookInfo();
            bookDao.add(book);
            showListBook(bookDao.getListBooks());
            clearBookInfo();
            showMessage("Thêm sách thành công!");
        } catch (Exception e) {
            showMessage("Lỗi khi thêm sách: " + e.getMessage());
        }
    }

    private void editBook() {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            try {
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
            } catch (Exception e) {
                showMessage("Lỗi khi sửa sách: " + e.getMessage());
            }
        }
    }

    private void deleteBook() {
        Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            try {
                bookDao.delete(selectedBook.getId());
                showListBook(bookDao.getListBooks());
                clearBookInfo();
                showMessage("Xóa sách thành công!");
            } catch (Exception e) {
                showMessage("Lỗi khi xóa sách: " + e.getMessage());
            }
        }
    }
    public void updateBookQuantity(String bookName, int change) {
        for (Book book : bookList) {
            if (book.getTenBook().equals(bookName)) {
                book.setSoLuong(book.getSoLuong() + change);
                break;
            }
        }
        bookTable.refresh(); // Cập nhật lại bảng để hiển thị số lượng mới
    }


    public Book getBookInfo() throws NumberFormatException {
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

    public void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setContentText(message);
        alert.showAndWait();
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
    public void addSwitchToLoanReturnViewListener(EventHandler<ActionEvent> handler) { switchToLoanReturnViewBtn.setOnAction(handler);
    }

    // Chuyển sang giao diện LoanReturnView
    private void switchToLoanReturnView() {
        Stage currentStage = (Stage) switchToLoanReturnViewBtn.getScene().getWindow();
        LoanReturnView loanReturnView = new LoanReturnView();
        try {
            loanReturnView.start(currentStage);
        } catch (Exception e) {
            showMessage("Lỗi khi chuyển sang giao diện LoanReturn: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
