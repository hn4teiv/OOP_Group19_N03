package com.mycompany.managelibrary.dao;

import com.mycompany.managelibrary.entity.LoanReturn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoanReturnDao {

    private static final Logger LOGGER = Logger.getLogger(LoanReturnDao.class.getName());

    // Database connection info
    private static final String URL = "jdbc:postgresql://pg-28f387a6-oopjava19.i.aivencloud.com:24516/users?sslmode=require";
    private static final String USER = "avnadmin";
    private static final String PASSWORD = "AVNS_la0WoxjlMLCV0uId8ze";

    // Fetch list of all loan returns
    public ObservableList<LoanReturn> getListLoanReturns() {
        ObservableList<LoanReturn> loanReturns = FXCollections.observableArrayList();
        String query = "SELECT * FROM loan_return"; // Tên bảng đã chỉnh sửa

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                LoanReturn loanReturn = new LoanReturn();
                loanReturn.setId(resultSet.getInt("id"));
                loanReturn.setDiaChi(resultSet.getString("dia_chi"));
                loanReturn.setTen(resultSet.getString("ten"));
                loanReturn.setLop(resultSet.getString("lop"));
                loanReturn.setTenSach(resultSet.getString("ten_sach"));
                loanReturn.setTrangThai(resultSet.getString("trang_thai"));
                loanReturns.add(loanReturn);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching loan returns", e);
        }

        return loanReturns;
    }

    // Add a new loan return to the database
    public void add(LoanReturn loanReturn) {

        String query = "INSERT INTO loan_return (dia_chi, ten, lop, ten_sach, trang_thai) VALUES (?, ?, ?, ?, ?)"; // Tên bảng đã chỉnh sửa

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, loanReturn.getDiaChi());
            preparedStatement.setString(2, loanReturn.getTen());
            preparedStatement.setString(3, loanReturn.getLop());
            preparedStatement.setString(4, loanReturn.getTenSach());
            preparedStatement.setString(5, loanReturn.getTrangThai());

            preparedStatement.executeUpdate();
            System.out.println("Thêm mượn trả thành công: " + loanReturn); // Thông báo thêm thành công
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding loan return", e);
            System.out.println("Lỗi khi thêm mượn trả: " + e.getMessage()); // Thông báo lỗi
        }
    }

    // Edit loan return information
    public void edit(LoanReturn loanReturn) {
        String query = "UPDATE loan_return SET dia_chi = ?, ten = ?, lop = ?, ten_sach = ?, trang_thai = ? WHERE id = ?"; // Tên bảng đã chỉnh sửa

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, loanReturn.getDiaChi());
            preparedStatement.setString(2, loanReturn.getTen());
            preparedStatement.setString(3, loanReturn.getLop());
            preparedStatement.setString(4, loanReturn.getTenSach());
            preparedStatement.setString(5, loanReturn.getTrangThai());
            preparedStatement.setInt(6, loanReturn.getId());

            preparedStatement.executeUpdate();
            System.out.println("Cập nhật mượn trả thành công: " + loanReturn); // Thông báo cập nhật thành công
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error editing loan return", e);
            System.out.println("Lỗi khi cập nhật mượn trả: " + e.getMessage()); // Thông báo lỗi
        }
    }

    // Delete a loan return by ID
    public boolean delete(int id) {
        String query = "DELETE FROM loan_return WHERE id = ?"; // Tên bảng đã chỉnh sửa
        boolean removed = false;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            removed = rowsAffected > 0;

            if (removed) {
                System.out.println("Xóa mượn trả thành công với ID: " + id); // Thông báo xóa thành công
            } else {
                System.out.println("Không tìm thấy mượn trả với ID: " + id); // Thông báo không tìm thấy
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting loan return", e);
            System.out.println("Lỗi khi xóa mượn trả: " + e.getMessage()); // Thông báo lỗi
        }

        return removed;
    }

    // Get a loan return by its ID
    public LoanReturn getLoanReturnById(int id) {
        String query = "SELECT * FROM loan_return WHERE id = ?"; // Tên bảng đã chỉnh sửa
        LoanReturn loanReturn = null;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                loanReturn = new LoanReturn();
                loanReturn.setId(resultSet.getInt("id"));
                loanReturn.setDiaChi(resultSet.getString("dia_chi"));
                loanReturn.setTen(resultSet.getString("ten"));
                loanReturn.setLop(resultSet.getString("lop"));
                loanReturn.setTenSach(resultSet.getString("ten_sach"));
                loanReturn.setTrangThai(resultSet.getString("trang_thai"));
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching loan return by ID", e);
        }

        return loanReturn;
    }
}
