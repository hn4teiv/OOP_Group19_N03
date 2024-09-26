package com.mycompany.managelibrary.entity;

public class Book {
    private int ID;
    private String tenSach;
    private double giaTien;
    private String soLuong;
    private String nhaXuatBan;
    private String tacGia;

    public Book(int ID, String tenSach, double giaTien, String soLuong, String nhaXuatBan, String tacGia) {
        this.ID = ID;
        this.tenSach = tenSach;
        this.giaTien = giaTien;
        this.soLuong = soLuong;
        this.nhaXuatBan = nhaXuatBan;
        this.tacGia = tacGia;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
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
