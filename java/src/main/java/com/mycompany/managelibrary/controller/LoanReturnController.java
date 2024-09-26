package com.mycompany.managelibrary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoanReturnController {
    private LoanReturnDao loanReturnDao;
    private LoanReturnView loanReturnView;
    private BookView bookView;

    public LoanReturnController(LoanReturnView loanReturnView, BookView bookView) {
        this.loanReturnView = loanReturnView;
        this.bookView = bookView;
        loanReturnView.addLoanReturnListener(new AddLoanReturnListener());
    }

    class AddLoanReturnListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        }
    }

    public void showLoanReturnView() {
    }
}
