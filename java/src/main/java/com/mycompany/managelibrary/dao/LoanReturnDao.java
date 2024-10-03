package com.mycompany.managelibrary.dao;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.mycompany.managelibrary.entity.LoanReturn;
import com.mycompany.managelibrary.entity.LoanReturnXML;

public class LoanReturnDao {
    private static final String FILE_PATH = "loan.xml";

    public List<LoanReturn> getListLoanReturn() {
        List<LoanReturn> loanReturns = new ArrayList<>();
        try {
            File xmlFile = new File(FILE_PATH);
            if (xmlFile.exists()) {
                JAXBContext jaxbContext = JAXBContext.newInstance(LoanReturnXML.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                LoanReturnXML loanReturnList = (LoanReturnXML) jaxbUnmarshaller.unmarshal(xmlFile);
                if (loanReturnList != null && loanReturnList.getLoanReturns() != null) {
                    loanReturns = loanReturnList.getLoanReturns();
                } else {
                    System.out.println("LoanReturnXML is null or LoanReturns list is empty");
                }
            } else {
                System.out.println("XML file not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loanReturns;
    }



    public void addLoanReturn(LoanReturn loanReturn) {
        List<LoanReturn> loanReturns = getListLoanReturn();
        int maxId = loanReturns.stream().mapToInt(LoanReturn::getId).max().orElse(0);
        loanReturn.setId(maxId + 1);
        loanReturns.add(loanReturn);
        writeListToXml(loanReturns);
    }

    public boolean updateLoanReturn(LoanReturn loanReturn) {
        List<LoanReturn> loanReturns = getListLoanReturn();
        boolean found = false;
        for (int i = 0; i < loanReturns.size(); i++) {
            if (loanReturns.get(i).getId() == loanReturn.getId()) {
                loanReturns.set(i, loanReturn);
                found = true;
                break;
            }
        }
        if (found) {
            writeListToXml(loanReturns);
        }
        return found;
    }

    public boolean deleteLoanReturn(int id) {
        List<LoanReturn> loanReturns = getListLoanReturn();
        boolean removed = loanReturns.removeIf(loanReturn -> loanReturn.getId() == id);
        if (removed) {
            for (int i = 0; i < loanReturns.size(); i++) {
                loanReturns.get(i).setId(i + 1);
            }
            writeListToXml(loanReturns);
        }
        return removed;
    }

    public List<LoanReturn> searchLoanReturnByTen(String ten) {
        List<LoanReturn> loanReturns = getListLoanReturn();
        return loanReturns.stream()
                .filter(loanReturn -> loanReturn.getTen().toLowerCase().contains(ten.toLowerCase()))
                .collect(Collectors.toList());
    }

    private void writeListToXml(List<LoanReturn> loanReturns) {
        try {
            File xmlFile = new File(FILE_PATH);
            JAXBContext jaxbContext = JAXBContext.newInstance(LoanReturnXML.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            LoanReturnXML loanReturnList = new LoanReturnXML();
            loanReturnList.setLoanReturns(loanReturns);

            jaxbMarshaller.marshal(loanReturnList, xmlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
