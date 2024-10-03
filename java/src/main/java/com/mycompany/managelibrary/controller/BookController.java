package com.mycompany.managelibrary.controller;

import java.util.List;

import com.mycompany.managelibrary.dao.BookDao;
import com.mycompany.managelibrary.entity.Book;
import com.mycompany.managelibrary.view.BookView;
import com.mycompany.managelibrary.view.LoanReturnView;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookController {
    private BookDao bookDao;
    private BookView bookView;
    private LoanReturnView loanReturnView;
    private Stage stage;  // Sử dụng một đối tượng Stage duy nhất

    public BookController(BookView bookView, Stage stage) {
        this.bookView = bookView;
        this.stage = stage;
        bookDao = new BookDao();

        // Thêm listeners cho BookView
        initializeListeners();

        // Hiển thị view ban đầu
        showBookView();
        bookView.addSwitchToLoanReturnViewListener(e -> switchToLoanReturnView());
    }

    private void initializeListeners() {
        bookView.addAddBookListener(event -> addBook());
        bookView.addEditBookListener(event -> editBook());
        bookView.addDeleteBookListener(event -> deleteBook());
        // Đảm bảo addSwitchToLoanReturnViewListener được gọi đúng
        bookView.addSwitchToLoanReturnViewListener(event -> switchToLoanReturnView());
    }

    public void showBookView() {
        List<Book> bookList = bookDao.getListBooks();
        bookView.showListBook(bookList);
        stage.show(); // Hiển thị cửa sổ BookView
    }

    public void hideLoanReturnView() {
        stage.hide();  // Sử dụng hide() từ Stage để ẩn cửa sổ
    }

    private void addBook() {
        Book book = bookView.getBookInfo();
        if (book != null) {
            bookDao.add(book);
            updateBookList("Thêm thành công!");
        }
    }

    private void editBook() {
        Book book = bookView.getBookInfo();
        if (book != null) {
            bookDao.edit(book);
            updateBookList("Cập nhật thành công!");
        }
    }

    private void deleteBook() {
        Book book = bookView.getBookInfo();
        if (book != null) {
            boolean deleted = bookDao.delete(book.getId());
            if (deleted) {
                updateBookList("Xóa thành công!");
            } else {
                bookView.showMessage("Xóa thất bại! Không tìm thấy sách để xóa.");
            }
        }
    }

    private void updateBookList(String successMessage) {
        List<Book> updatedList = bookDao.getListBooks();
        bookView.showListBook(updatedList);
        bookView.clearBookInfo();
        bookView.showMessage(successMessage);
    }

    // Chuyển đổi sang LoanReturnView
    // Chuyển đổi sang LoanReturnView
    public void switchToLoanReturnView() {
        // Tạo cửa sổ LoanReturnView mới
        loanReturnView = new LoanReturnView(); // Tạo đối tượng LoanReturnView

        // Tạo Stage mới cho LoanReturnView và gán Scene cho nó
        Stage loanReturnStage = new Stage();
        loanReturnStage.setScene(new Scene(loanReturnView.getView())); // Tạo Scene từ layout của LoanReturnView
        loanReturnStage.setTitle("Quản lý mượn trả sách");
        loanReturnStage.show();  // Hiển thị cửa sổ LoanReturnView

        // Ẩn cửa sổ BookView
        stage.hide(); // Ẩn BookView
    }



}
