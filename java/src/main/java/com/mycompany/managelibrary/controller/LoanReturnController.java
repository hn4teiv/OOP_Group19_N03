package com.mycompany.managelibrary.controller;

import com.mycompany.managelibrary.dao.LoanReturnDao;
import com.mycompany.managelibrary.view.BookView;
import com.mycompany.managelibrary.view.LoanReturnView;

public class LoanReturnController {
    private LoanReturnDao loanReturnDao;
    private LoanReturnView loanReturnView;
    private BookView bookView;

    public LoanReturnController(LoanReturnView loanReturnView, BookView bookView) {
        this.loanReturnView = loanReturnView;
        this.bookView = bookView;

        // Thêm lắng nghe cho nút thêm
        loanReturnView.addLoanReturnListener(new LoanReturnView.AddLoanReturnListener() {
            @Override
            public void onAddLoanReturn() {
                // Logic để thêm LoanReturn vào cơ sở dữ liệu hoặc danh sách
                System.out.println("Loan return added");
            }
        });
    }
}
