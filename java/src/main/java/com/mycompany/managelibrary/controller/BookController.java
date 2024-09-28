package com.mycompany.managelibrary.controller;

import com.mycompany.managelibrary.entity.Book;
import com.mycompany.managelibrary.view.BookView;
import com.mycompany.managelibrary.view.LoanReturnView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookController {
    private Book book;
    private BookView bookView;
    private LoanReturnView loanReturnView;

    public BookController(BookView bookView, LoanReturnView loanReturnView) {
        this.bookView = bookView;
        this.loanReturnView = loanReturnView;
        bookView.addAddBookListener(new AddBookListener()); // Sửa từ addBookListener thành addAddBookListener
        bookView.addEditBookListener(new EditBookListener()); // Sửa từ editBookListener thành addEditBookListener
        bookView.addDeleteBookListener(new DeleteBookListener()); // Sửa từ deleteBookListener thành addDeleteBookListener
    }


    public void showBookView() {
    }

    class AddBookListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        }
    }

    class EditBookListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        }
    }

    class DeleteBookListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        }
    }
}

