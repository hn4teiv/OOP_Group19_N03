package com.mycompany.managelibrary.view;

import com.mycompany.managelibrary.entity.LoanReturn;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoanReturnView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField idField;
    private JTextField diaChiField;
    private JTextField tenSachField;
    private JTextField lopField;
    private JTextField trangThaiField;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;

    private ArrayList<LoanReturn> loanReturnList;

    public LoanReturnView() {
        setTitle("Loan Return Management");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        loanReturnList = new ArrayList<>();

        // Tạo bảng và mô hình bảng
        tableModel = new DefaultTableModel(new String[]{"ID", "Address", "Book Title", "Class", "Status"}, 0);
        table = new JTable(tableModel);

        // Tạo các trường nhập liệu
        idField = new JTextField(15);
        diaChiField = new JTextField(15);
        tenSachField = new JTextField(15);
        lopField = new JTextField(15);
        trangThaiField = new JTextField(15);

        addButton = new JButton("Add LoanReturn");
        editButton = new JButton("Edit LoanReturn");
        deleteButton = new JButton("Delete LoanReturn");

        // Thiết lập bố trí giao diện
        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        formPanel.add(new JLabel("ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Address:"));
        formPanel.add(diaChiField);
        formPanel.add(new JLabel("Book Title:"));
        formPanel.add(tenSachField);
        formPanel.add(new JLabel("Class:"));
        formPanel.add(lopField);
        formPanel.add(new JLabel("Status:"));
        formPanel.add(trangThaiField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Thêm bảng và các thành phần khác vào cửa sổ
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(formPanel, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.NORTH);

        // Thêm sự kiện cho các nút
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addLoanReturn();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editLoanReturn();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteLoanReturn();
            }
        });
    }

    private void addLoanReturn() {
        LoanReturn loanReturn = new LoanReturn(
                Integer.parseInt(idField.getText()),
                diaChiField.getText(),
                tenSachField.getText(),
                lopField.getText(),
                trangThaiField.getText()
        );
        loanReturnList.add(loanReturn);
        tableModel.addRow(new Object[]{
                loanReturn.getId(),
                loanReturn.getDiaChi(),
                loanReturn.getTenSach(),
                loanReturn.getLop(),
                loanReturn.getTrangThai()
        });
        clearForm();
    }

    private void editLoanReturn() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            LoanReturn selectedLoanReturn = loanReturnList.get(selectedRow);

            selectedLoanReturn.setDiaChi(diaChiField.getText());
            selectedLoanReturn.setTenSach(tenSachField.getText());
            selectedLoanReturn.setLop(lopField.getText());
            selectedLoanReturn.setTrangThai(trangThaiField.getText());

            tableModel.setValueAt(selectedLoanReturn.getDiaChi(), selectedRow, 1);
            tableModel.setValueAt(selectedLoanReturn.getTenSach(), selectedRow, 2);
            tableModel.setValueAt(selectedLoanReturn.getLop(), selectedRow, 3);
            tableModel.setValueAt(selectedLoanReturn.getTrangThai(), selectedRow, 4);
            clearForm();
        }
    }

    private void deleteLoanReturn() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            loanReturnList.remove(selectedRow);
            tableModel.removeRow(selectedRow);
            clearForm();
        }
    }

    private void clearForm() {
        idField.setText("");
        diaChiField.setText("");
        tenSachField.setText("");
        lopField.setText("");
        trangThaiField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoanReturnView loanReturnView = new LoanReturnView();
            loanReturnView.setVisible(true);
        });
    }
    public void addLoanReturnListener(AddLoanReturnListener listener) {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.onAddLoanReturn();
            }
        });
    }

    public interface AddLoanReturnListener {
        void onAddLoanReturn();
    }

}
