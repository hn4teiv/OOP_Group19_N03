package com.mycompany.managelibrary.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mycompany.managelibrary.entity.LoanReturn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoanReturnDao {
    // Thông tin kết nối cơ sở dữ liệu
    private static final String URL = "jdbc:postgresql://pg-28f387a6-oopjava19.i.aivencloud.com:24516/users?sslmode=require";
    private static final String USER = "";
    private static final String PASSWORD = "";

    // Lấy kết nối đến cơ sở dữ liệu
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Lấy danh sách tất cả các loan returns từ cơ sở dữ liệu
    public ObservableList<LoanReturn> getListLoanReturns() throws SQLException {
        ObservableList<LoanReturn> loanReturns = FXCollections.observableArrayList();
        String sql = "SELECT * FROM loan_return";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

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
            System.err.println("Lỗi khi lấy danh sách loan returns: " + e.getMessage());
        }

        return loanReturns;
    }

    // Thêm một loan return mới
    public void addLoanReturn(LoanReturn loanReturn) throws SQLException {
        String sql = "INSERT INTO loan_return (dia_chi, ten, lop, ten_sach, trang_thai) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, loanReturn.getDiaChi());
            pstmt.setString(2, loanReturn.getTen());
            pstmt.setString(3, loanReturn.getLop());
            pstmt.setString(4, loanReturn.getTenSach());
            pstmt.setString(5, loanReturn.getTrangThai());
            pstmt.executeUpdate();
            System.out.println("Thêm thành công LoanReturn: " + loanReturn);
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm loan return: " + e.getMessage());
        }
    }

    // Cập nhật một loan return
    public boolean updateLoanReturn(LoanReturn loanReturn) throws SQLException {
        String sql = "UPDATE loan_return SET dia_chi = ?, ten = ?, lop = ?, ten_sach = ?, trang_thai = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, loanReturn.getDiaChi());
            pstmt.setString(2, loanReturn.getTen());
            pstmt.setString(3, loanReturn.getLop());
            pstmt.setString(4, loanReturn.getTenSach());
            pstmt.setString(5, loanReturn.getTrangThai());
            pstmt.setInt(6, loanReturn.getId());
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng khi cập nhật: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật loan return: " + e.getMessage());
            return false;
        }
    }

    // Xóa một loan return
    public boolean deleteLoanReturn(int id) throws SQLException {
        String sql = "DELETE FROM loan_return WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng khi xóa: " + rowsAffected);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi khi xóa loan return: " + e.getMessage());
            return false;
        }
    }

}