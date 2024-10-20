package com.mycompany.managelibrary.controller;

import com.mycompany.managelibrary.dao.LoanReturnDao;
import com.mycompany.managelibrary.entity.LoanReturn;
import com.mycompany.managelibrary.view.LoanReturnView;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoanReturnController {
    private LoanReturnDao loanReturnDao;
//    private LoanReturnView loanReturnView;
    private Stage stage;
    protected LoanReturnView loanReturnView;



    public LoanReturnController(LoanReturnView loanReturnView, Stage stage) {
        this.loanReturnView = loanReturnView;
        this.stage = stage;
        this.loanReturnDao = new LoanReturnDao();

        // Initialize listeners for LoanReturnView
        initializeListeners();
    }

    private void initializeListeners() {
        loanReturnView.addAddLoanReturnListener(event -> addLoanReturn());
        loanReturnView.addEditLoanReturnListener(event -> editLoanReturn());
        loanReturnView.addDeleteLoanReturnListener(event -> deleteLoanReturn());
        loanReturnView.addSwitchToBookViewListener(event -> switchToBookView());
    }

    // Show Loan Return View and load data
    public void showLoanReturnView() {
        ObservableList<LoanReturn> loanReturnList = loanReturnDao.getListLoanReturns();
        loanReturnView.showListLoanReturn(loanReturnList);
        stage.setScene(new Scene(loanReturnView.createLayout()));
        stage.show();
    }

    // Add a new loan return
    private void addLoanReturn() {
        LoanReturn loanReturn = loanReturnView.getLoanReturnInfo();
        if (loanReturn != null) {
            loanReturnDao.add(loanReturn);
            updateLoanReturnList("Thêm mượn trả thành công!");
        } else {
            loanReturnView.showMessage("Vui lòng nhập thông tin hợp lệ trước khi thêm.");
        }
    }

    // Edit loan return details
    private void editLoanReturn() {
        LoanReturn loanReturn = loanReturnView.getLoanReturnInfo();
        if (loanReturn != null) {
            loanReturnDao.edit(loanReturn);
            updateLoanReturnList("Cập nhật mượn trả thành công!");
        } else {
            loanReturnView.showMessage("Vui lòng chọn thông tin hợp lệ trước khi chỉnh sửa.");
        }
    }

    // Delete a loan return entry
    private void deleteLoanReturn() {
        LoanReturn loanReturn = loanReturnView.getLoanReturnInfo();
        if (loanReturn != null) {
            boolean deleted = loanReturnDao.delete(loanReturn.getId());
            if (deleted) {
                updateLoanReturnList("Xóa mượn trả thành công!");
            } else {
                loanReturnView.showMessage("Xóa thất bại! Không tìm thấy thông tin mượn trả để xóa.");
            }
        } else {
            loanReturnView.showMessage("Vui lòng chọn thông tin hợp lệ trước khi xóa.");
        }
    }

    // Update list after changes
    private void updateLoanReturnList(String successMessage) {
        ObservableList<LoanReturn> updatedList = loanReturnDao.getListLoanReturns();
        loanReturnView.showListLoanReturn(updatedList);
        loanReturnView.clearLoanReturnInfo();
        loanReturnView.showMessage(successMessage);
    }

    // Switch to the BookView
    private void switchToBookView() {
        // This will be handled in BookController; LoanReturnView will be hidden
        stage.hide();
    }
}
