package com.mycompany.managelibrary.dao;

import com.mycompany.managelibrary.entity.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    // Thông tin kết nối cơ sở dữ liệu
    private static final String URL = "jdbc:postgresql://pg-28f387a6-oopjava19.i.aivencloud.com:24516/users?sslmode=require";
    private static final String USER = "";
    private static final String PASSWORD = "";

    // Lấy danh sách tất cả các sách từ cơ sở dữ liệu
    public ObservableList<Book> getListBooks() {
        ObservableList<Book> books = FXCollections.observableArrayList();
        String query = "SELECT * FROM books";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTenBook(resultSet.getString("ten_book"));
                book.setLoaiBook(resultSet.getString("loai_book"));
                book.setGiaThanh(resultSet.getDouble("gia_thanh"));
                book.setSoLuong(resultSet.getInt("so_luong"));
                book.setMaSo(resultSet.getString("ma_so"));
                book.setNhaXuatBan(resultSet.getString("nha_xuat_ban"));
                book.setTacGia(resultSet.getString("tac_gia"));
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    // Thêm một sách vào cơ sở dữ liệu
    public void add(Book book) {
        String query = "INSERT INTO books (ten_book, loai_book, gia_thanh, so_luong, ma_so, nha_xuat_ban, tac_gia) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, book.getTenBook());
            preparedStatement.setString(2, book.getLoaiBook());
            preparedStatement.setDouble(3, book.getGiaThanh());
            preparedStatement.setInt(4, book.getSoLuong());
            preparedStatement.setString(5, book.getMaSo());
            preparedStatement.setString(6, book.getNhaXuatBan());
            preparedStatement.setString(7, book.getTacGia());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Sửa thông tin sách trong cơ sở dữ liệu
    public void edit(Book book) {
        String query = "UPDATE books SET ten_book = ?, loai_book = ?, gia_thanh = ?, so_luong = ?, ma_so = ?, nha_xuat_ban = ?, tac_gia = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, book.getTenBook());
            preparedStatement.setString(2, book.getLoaiBook());
            preparedStatement.setDouble(3, book.getGiaThanh());
            preparedStatement.setInt(4, book.getSoLuong());
            preparedStatement.setString(5, book.getMaSo());
            preparedStatement.setString(6, book.getNhaXuatBan());
            preparedStatement.setString(7, book.getTacGia());
            preparedStatement.setInt(8, book.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa một sách khỏi cơ sở dữ liệu
    public boolean delete(int id) {
        String query = "DELETE FROM books WHERE id = ?";
        boolean removed = false;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            removed = rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return removed;
    }

    // Lấy một sách theo ID
    public Book getBookById(int id) {
        String query = "SELECT * FROM books WHERE id = ?";
        Book book = null;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTenBook(resultSet.getString("ten_book"));
                book.setLoaiBook(resultSet.getString("loai_book"));
                book.setGiaThanh(resultSet.getDouble("gia_thanh"));
                book.setSoLuong(resultSet.getInt("so_luong"));
                book.setMaSo(resultSet.getString("ma_so"));
                book.setNhaXuatBan(resultSet.getString("nha_xuat_ban"));
                book.setTacGia(resultSet.getString("tac_gia"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }
}
