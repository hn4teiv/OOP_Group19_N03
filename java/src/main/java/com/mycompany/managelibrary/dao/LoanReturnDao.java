package com.mycompany.managelibrary.dao;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.managelibrary.entity.LoanReturn;

public class LoanReturnDao {
    private static final String FILE_PATH = "loanreturn.xml";
    private List<LoanReturn> loanReturns = new ArrayList<>();

    public List<LoanReturn> getListLoanReturn() {
        return loanReturns;
    }

    public void add(LoanReturn loanReturn) {
        loanReturns.add(loanReturn);
    }

    public void editLoanReturn(LoanReturn loanReturn) {
    }

    public boolean deleteLoanReturn(int id) {
        return true;
    }
}
