package com.mycompany.managelibrary.controller;

import com.mycompany.managelibrary.dao.LoanReturnDao;
import com.mycompany.managelibrary.entity.LoanReturn;
import com.mycompany.managelibrary.view.BookView;
import com.mycompany.managelibrary.view.LoanReturnView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.List;

public class LoanReturnController {
    private LoanReturnDao loanReturnDao;
    private LoanReturnView loanReturnView;
    private BookView bookView;
    private Stage loanReturnStage;  // Stage để hiển thị LoanReturnView
    private Stage bookStage;        // Stage để hiển thị BookView

    public LoanReturnController(LoanReturnView loanReturnView, BookView bookView) {
        this.loanReturnView = loanReturnView;
        this.bookView = bookView;
        this.loanReturnDao = new LoanReturnDao();

        setupStages();  // Khởi tạo các Stage cho LoanReturnView và BookView
        setupListeners();  // Thiết lập các listener
    }

    // Cấu hình Stage cho LoanReturnView và BookView
    private void setupStages() {
        // Tạo Stage cho LoanReturnView
        loanReturnStage = new Stage();
        loanReturnStage.setTitle("Quản lý Mượn Trả");
        loanReturnStage.setScene(new Scene(loanReturnView.getView(), 800, 600));  // Đảm bảo loanReturnView.getView() trả về Parent
        loanReturnStage.show();

        // Tạo Stage cho BookView
        bookStage = new Stage();
        bookStage.setTitle("Quản lý Sách");
        bookStage.setScene(new Scene(bookView.getLayout(), 800, 600));  // Cài đặt Scene cho BookView
    }

    // Hiển thị LoanReturnView
    public void showLoanReturnView() {
        try {
            List<LoanReturn> loanReturnList = loanReturnDao.getListLoanReturn();
            loanReturnView.showListLoanReturn(loanReturnList);
            loanReturnStage.show();  // Hiển thị Stage của LoanReturnView
        } catch (Exception e) {
            loanReturnView.showMessage("Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }

    private void setupListeners() {
        loanReturnView.addAddLoanReturnListener(new AddLoanReturnListener());
        loanReturnView.addEditLoanReturnListener(new EditLoanReturnListener());
        loanReturnView.addDeleteLoanReturnListener(new DeleteLoanReturnListener());
        loanReturnView.addSwitchToBookViewListener(new SwitchToBookViewListener());
    }

    public class AddLoanReturnListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            LoanReturn loanReturn = loanReturnView.getLoanReturnInfo();
            if (loanReturn != null) {
                try {
                    loanReturnDao.addLoanReturn(loanReturn);
                    refreshView();  // Cập nhật lại giao diện sau khi thêm thành công
                    loanReturnView.showMessage("Thêm thành công!");
                } catch (Exception e) {
                    loanReturnView.showMessage("Lỗi khi thêm: " + e.getMessage());
                }
            } else {
                loanReturnView.showMessage("Vui lòng điền đầy đủ thông tin.");
            }
        }
    }

    public class EditLoanReturnListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            LoanReturn loanReturn = loanReturnView.getLoanReturnInfo();
            if (loanReturn != null) {
                try {
                    loanReturnDao.updateLoanReturn(loanReturn);
                    refreshView();
                    loanReturnView.showMessage("Cập nhật thành công!");
                } catch (Exception e) {
                    loanReturnView.showMessage("Lỗi khi cập nhật: " + e.getMessage());
                }
            }
        }
    }

    public class DeleteLoanReturnListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            LoanReturn loanReturn = loanReturnView.getLoanReturnInfo();
            if (loanReturn != null) {
                try {
                    boolean deleted = loanReturnDao.deleteLoanReturn(loanReturn.getId());
                    if (deleted) {
                        refreshView();
                        loanReturnView.showMessage("Xóa thành công!");
                    } else {
                        loanReturnView.showMessage("Không tìm thấy mượn trả để xóa.");
                    }
                } catch (Exception e) {
                    loanReturnView.showMessage("Lỗi khi xóa: " + e.getMessage());
                }
            }
        }
    }

    // Listener cho việc chuyển đổi giữa LoanReturnView và BookView
    public class SwitchToBookViewListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            // Hiển thị BookView và ẩn LoanReturnView
            bookStage.show();  // Hiển thị Stage của BookView
            loanReturnStage.hide();  // Ẩn Stage của LoanReturnView
        }
    }

    private void refreshView() {
        try {
            List<LoanReturn> updatedList = loanReturnDao.getListLoanReturn();
            loanReturnView.showListLoanReturn(updatedList);
            loanReturnView.clearLoanReturnInfo();
            loanReturnView.resetButtons();
        } catch (Exception e) {
            loanReturnView.showMessage("Lỗi khi làm mới dữ liệu: " + e.getMessage());
        }
    }
}
