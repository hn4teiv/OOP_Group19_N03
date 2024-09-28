package com.mycompany.managelibrary.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import com.mycompany.managelibrary.entity.Book;

public class BookView extends JFrame implements ListSelectionListener {
    private static final long serialVersionUID = 1L;

    private DefaultTableModel tableModel;
    private JTable bookTable;
    private JScrollPane jScrollPaneBookTable;
    private JTextField tenBookField, loaiBookField, giaThanhField, soLuongField, maSoField, nhaXuatBanField, tacGiaField, searchField;
    private JButton addBookBtn, editBookBtn, deleteBookBtn, searchButton, switchToMuonTraViewBtn;
    private String[] columnNames = {"ID", "Tên Sách", "Loại Sách", "Giá Thành", "Số Lượng", "Mã Số", "Nhà Xuất Bản", "Tác Giả"};

    public BookView() {
        setTitle("Quản lý Sách");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents();
        setLayouts();
        setupListeners();
    }

    private void initializeComponents() {
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnNames);

        bookTable = new JTable(tableModel);
        bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jScrollPaneBookTable = new JScrollPane(bookTable);
        jScrollPaneBookTable.setPreferredSize(new Dimension(700, 300));

        tenBookField = new JTextField(20);
        loaiBookField = new JTextField(20);
        giaThanhField = new JTextField(10);
        soLuongField = new JTextField(5);
        maSoField = new JTextField(10);
        nhaXuatBanField = new JTextField(20);
        tacGiaField = new JTextField(20);

        addBookBtn = new JButton("Thêm");
        editBookBtn = new JButton("Sửa");
        deleteBookBtn = new JButton("Xóa");

        searchField = new JTextField(20);
        searchButton = new JButton("Tìm kiếm");

        switchToMuonTraViewBtn = new JButton("Chuyển sang Mượn Trả");
    }

    private void setLayouts() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(8, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("Tên Sách:"));
        formPanel.add(tenBookField);
        formPanel.add(new JLabel("Loại Sách:"));
        formPanel.add(loaiBookField);
        formPanel.add(new JLabel("Giá Thành:"));
        formPanel.add(giaThanhField);
        formPanel.add(new JLabel("Số Lượng:"));
        formPanel.add(soLuongField);
        formPanel.add(new JLabel("Mã Số:"));
        formPanel.add(maSoField);
        formPanel.add(new JLabel("Nhà Xuất Bản:"));
        formPanel.add(nhaXuatBanField);
        formPanel.add(new JLabel("Tác Giả:"));
        formPanel.add(tacGiaField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonPanel.add(addBookBtn);
        buttonPanel.add(editBookBtn);
        buttonPanel.add(deleteBookBtn);
        buttonPanel.add(switchToMuonTraViewBtn);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        panel.add(formPanel);
        panel.add(jScrollPaneBookTable);
        panel.add(buttonPanel);
        panel.add(searchPanel);

        add(panel);
    }

    private void setupListeners() {
        bookTable.getSelectionModel().addListSelectionListener(this);
    }

    public void addAddBookListener(ActionListener listener) {
        addBookBtn.addActionListener(listener);
    }

    public void addEditBookListener(ActionListener listener) {
        editBookBtn.addActionListener(listener);
    }

    public void addDeleteBookListener(ActionListener listener) {
        deleteBookBtn.addActionListener(listener);
    }

    public void addSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public void addSwitchToMuonTraViewListener(ActionListener listener) {
        switchToMuonTraViewBtn.addActionListener(listener);
    }

    public void resetButtons() {
        addBookBtn.setEnabled(true);
        editBookBtn.setEnabled(false);
        deleteBookBtn.setEnabled(false);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showListBook(List<Book> list) {
        int size = list.size();
        tableModel.setRowCount(0);
        Object[][] bookObjects = new Object[size][8];
        for (int i = 0; i < size; i++) {
            Book bk = list.get(i);
            String formattedGiaThanh = String.format("%,.0f", bk.getGiaThanh());
            bookObjects[i][0] = bk.getId();
            bookObjects[i][1] = bk.getTenBook();
            bookObjects[i][2] = bk.getLoaiBook();
            bookObjects[i][3] = formattedGiaThanh;
            bookObjects[i][4] = bk.getSoLuong();
            bookObjects[i][5] = bk.getMaSo(); // Thêm trường Ma So
            bookObjects[i][6] = bk.getNhaXuatBan();
            bookObjects[i][7] = bk.getTacGia();
        }
        bookTable.setModel(new DefaultTableModel(bookObjects, columnNames));
    }

    public void fillBookFromSelectedRow() {
        int row = bookTable.getSelectedRow();
        if (row != -1) {
            tenBookField.setText(bookTable.getValueAt(row, 1).toString());
            loaiBookField.setText(bookTable.getValueAt(row, 2).toString());
            giaThanhField.setText(bookTable.getValueAt(row, 3).toString());
            soLuongField.setText(bookTable.getValueAt(row, 4).toString());
            maSoField.setText(bookTable.getValueAt(row, 5).toString());
            nhaXuatBanField.setText(bookTable.getValueAt(row, 6).toString());
            tacGiaField.setText(bookTable.getValueAt(row, 7).toString());
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        fillBookFromSelectedRow();
    }

    public static void main(String[] args) {
        new BookView();
    }
}

