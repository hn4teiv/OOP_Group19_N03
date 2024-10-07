package com.mycompany.managelibrary.controller;

import com.mycompany.managelibrary.dao.LoanReturnDao;
import com.mycompany.managelibrary.entity.LoanReturn;
import com.mycompany.managelibrary.view.LoanReturnView;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class LoanReturnController {
    private LoanReturnView view;
    private LoanReturnDao loanReturnDAO;

    public LoanReturnController(LoanReturnView view) {
        this.view = view;
        this.loanReturnDAO = new LoanReturnDao();
        loadLoanReturns();
        setupListeners();
    }

    private void loadLoanReturns() {
        try {
            ObservableList<LoanReturn> loanReturns = loanReturnDAO.getListLoanReturns();
            view.setLoanReturnList(loanReturns);
        } catch (SQLException e) {
            System.err.println("Lỗi khi tải danh sách loan returns: " + e.getMessage());
        }
    }

    private void setupListeners() {
        view.addAddLoanReturnListener(event -> addLoanReturn());
        view.addEditLoanReturnListener(event -> editLoanReturn());
        view.addDeleteLoanReturnListener(event -> deleteLoanReturn());
    }

    private void addLoanReturn() {
        try {
            LoanReturn loanReturn = view.getLoanReturnFromInput();
            loanReturnDAO.addLoanReturn(loanReturn);
            loadLoanReturns(); // Cập nhật lại danh sách
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm loan return: " + e.getMessage());
        }
    }

    private void editLoanReturn() {
        int selectedId = view.getSelectedLoanReturnId();
        if (selectedId != -1) {
            try {
                LoanReturn loanReturn = view.getLoanReturnFromInput();
                loanReturn.setId(selectedId); // Đặt ID để cập nhật
                loanReturnDAO.updateLoanReturn(loanReturn);
                loadLoanReturns(); // Cập nhật lại danh sách
            } catch (SQLException e) {
                System.err.println("Lỗi khi cập nhật loan return: " + e.getMessage());
            }
        }
    }

    private void deleteLoanReturn() {
        int selectedId = view.getSelectedLoanReturnId();
        if (selectedId != -1) {
            try {
                loanReturnDAO.deleteLoanReturn(selectedId);
                loadLoanReturns(); // Cập nhật lại danh sách
            } catch (SQLException e) {
                System.err.println("Lỗi khi xóa loan return: " + e.getMessage());
            }
        }
    }
}
