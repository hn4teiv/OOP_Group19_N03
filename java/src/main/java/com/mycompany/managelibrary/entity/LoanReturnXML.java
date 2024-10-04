package com.mycompany.managelibrary.entity;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LoanReturns")  // Tên gốc cho phần tử XML
@XmlAccessorType(XmlAccessType.FIELD)  // Kiểm soát cách JAXB truy cập vào các trường
public class LoanReturnXML {

    @XmlElement(name = "LoanReturn")  // Tên phần tử cho mỗi đối tượng LoanReturn trong XML
    private List<LoanReturn> loanReturns = null;  // Danh sách các đối tượng LoanReturn

    // Phương thức lấy danh sách LoanReturn
    public List<LoanReturn> getLoanReturns() {
        return loanReturns;
    }

    // Phương thức thiết lập danh sách LoanReturn
    public void setLoanReturns(List<LoanReturn> loanReturns) {
        this.loanReturns = loanReturns;
    }
}
