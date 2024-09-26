package com.mycompany.managelibrary.view;


public class Book {
    private int id;
    private String tenBook;
    private String loaiBook;
    private double giaThanh;
    private int soLuong;
    private String maSo;
    private String nhaXuatBan;
    private String tacGia;

    public Book() {
    }

    public Book(int id, String tenBook, String loaiBook, double giaThanh, int soLuong, String maSo, String nhaXuatBan, String tacGia) {
        this.id = id;
        this.tenBook = tenBook;
        this.loaiBook = loaiBook;
        this.giaThanh = giaThanh;
        this.soLuong = soLuong;
        this.maSo = maSo;
        this.nhaXuatBan = nhaXuatBan;
        this.tacGia = tacGia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenBook() {
        return tenBook;
    }

    public void setTenBook(String tenBook) {
        this.tenBook = tenBook;
    }

    public String getLoaiBook() {
        return loaiBook;
    }

    public void setLoaiBook(String loaiBook) {
        this.loaiBook = loaiBook;
    }
uatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }
}
    public double getGiaThanh() {
        return giaThanh;
    }

    public void setGiaThanh(double giaThanh) {
        this.giaThanh = giaThanh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMaSo() {
        return maSo;
    }

    public void setMaSo(String maSo) {
        this.maSo = maSo;
    }

    public String getNhaX