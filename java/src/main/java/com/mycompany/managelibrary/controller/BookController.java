package com.mycompany.managelibrary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookController {
    private Book book;
    private BookView bookView;
    private LoanReturnView loanReturnView;

    public BookController(BookView bookView, LoanReturnView loanReturnView) {
        this.bookView = bookView;
        this.loanReturnView = loanReturnView;
        bookView.addBookListener(new AddBookListener());
        bookView.editBookListener(new EditBookListener());
        bookView.deleteBookListener(new DeleteBookListener());
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

