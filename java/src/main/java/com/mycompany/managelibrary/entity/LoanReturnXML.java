package com.mycompany.managelibrary.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class LoanReturnXML {
    private List<LoanReturn> loanReturns;
    private static final String FILE_PATH = "loanreturn.xml";

    public LoanReturnXML() {
        this.loanReturns = new ArrayList<>();
    }

    public List<LoanReturn> getLoanReturns() {
        return loanReturns;
    }

    public void setLoanReturns(List<LoanReturn> loanReturns) {
        this.loanReturns = loanReturns;
    }

    public void loadLoanReturns() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                JAXBContext context = JAXBContext.newInstance(LoanReturnListWrapper.class);
                Unmarshaller um = context.createUnmarshaller();

                LoanReturnListWrapper wrapper = (LoanReturnListWrapper) um.unmarshal(file);
                this.loanReturns = wrapper.getLoanReturns();
            } else {
                this.loanReturns = new ArrayList<>();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void saveLoanReturns() {
        try {
            JAXBContext context = JAXBContext.newInstance(LoanReturnListWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            LoanReturnListWrapper wrapper = new LoanReturnListWrapper();
            wrapper.setLoanReturns(this.loanReturns);

            marshaller.marshal(wrapper, new File(FILE_PATH));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static class LoanReturnListWrapper {
        private List<LoanReturn> loanReturns;

        public List<LoanReturn> getLoanReturns() {
            return loanReturns;
        }

        public void setLoanReturns(List<LoanReturn> loanReturns) {
            this.loanReturns = loanReturns;
        }
    }
}
