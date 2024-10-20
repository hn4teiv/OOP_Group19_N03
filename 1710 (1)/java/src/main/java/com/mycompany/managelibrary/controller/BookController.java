package com.mycompany.managelibrary.controller;

import com.mycompany.managelibrary.dao.BookDao;
import com.mycompany.managelibrary.entity.Book;
import com.mycompany.managelibrary.view.BookView;
import com.mycompany.managelibrary.view.LoanReturnView;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookController {
    private BookDao bookDao;
    private BookView bookView;
    private Stage stage;
    private LoanReturnController loanReturnController;

    public BookController(BookView bookView, Stage stage, LoanReturnView loanReturnView) {
        this.bookView = bookView;
        this.stage = stage;
        this.bookDao = new BookDao();
        this.loanReturnController = new LoanReturnController(loanReturnView, stage); // Initialize LoanReturnController

        // Initialize listeners for BookView
        initializeListeners();
    }

    private void initializeListeners() {
        bookView.addAddBookListener(event -> addBook());
        bookView.addEditBookListener(event -> editBook());
        bookView.addDeleteBookListener(event -> deleteBook());
        bookView.addSwitchToLoanReturnViewListener(event -> switchToLoanReturnView());
    }

    // Add a new book
    private void addBook() {
        Book book = bookView.getBookInfo();
        if (book != null) {
            bookDao.add(book);
            updateBookList("Thêm sách thành công!");
        } else {
            bookView.showMessage("Vui lòng nhập thông tin hợp lệ trước khi thêm.");
        }
    }

    // Edit book details
    private void editBook() {
        Book book = bookView.getBookInfo();
        if (book != null) {
            bookDao.edit(book);
            updateBookList("Cập nhật sách thành công!");
        } else {
            bookView.showMessage("Vui lòng chọn thông tin hợp lệ trước khi chỉnh sửa.");
        }
    }

    // Delete a book entry
    private void deleteBook() {
        Book book = bookView.getBookInfo();
        if (book != null) {
            boolean deleted = bookDao.delete(book.getId());
            if (deleted) {
                updateBookList("Xóa sách thành công!");
            } else {
                bookView.showMessage("Xóa thất bại! Không tìm thấy sách để xóa.");
            }
        } else {
            bookView.showMessage("Vui lòng chọn thông tin hợp lệ trước khi xóa.");
        }
    }

    // Update list after changes
    private void updateBookList(String successMessage) {
        ObservableList<Book> updatedList = bookDao.getListBooks();
        bookView.showListBook(updatedList);
        bookView.clearBookInfo();
        bookView.showMessage(successMessage);
    }

    // Switch to LoanReturnView
    private void switchToLoanReturnView() {
        loanReturnController.showLoanReturnView(); // Show LoanReturnView
        stage.setScene(new Scene(loanReturnController.loanReturnView.createLayout()));
        stage.show();
    }
}
