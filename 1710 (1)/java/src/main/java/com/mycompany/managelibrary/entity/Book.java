package com.mycompany.managelibrary.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class Book {
    private int id;
    private String tenBook;
    private String loaiBook;
    private double giaThanh;
    private int soLuong;
    private String maSo;
    private String nhaXuatBan;
    private String tacGia;

    // Getters và Setters

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

    public String getNhaXuatBan() {
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
