package com.mycompany.managelibrary.entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LoanReturn {
    private SimpleIntegerProperty id;
    private SimpleStringProperty diaChi;
    private SimpleStringProperty ten;
    private SimpleStringProperty lop;
    private SimpleStringProperty tenSach;
    private SimpleStringProperty trangThai;

    public LoanReturn() {
        this.id = new SimpleIntegerProperty();
        this.diaChi = new SimpleStringProperty();
        this.ten = new SimpleStringProperty();
        this.lop = new SimpleStringProperty();
        this.tenSach = new SimpleStringProperty();
        this.trangThai = new SimpleStringProperty();
    }

    // Getter và Setter cho các thuộc tính
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getDiaChi() {
        return diaChi.get();
    }

    public void setDiaChi(String diaChi) {
        this.diaChi.set(diaChi);
    }

    public SimpleStringProperty diaChiProperty() {
        return diaChi;
    }

    public String getTen() {
        return ten.get();
    }

    public void setTen(String ten) {
        this.ten.set(ten);
    }

    public SimpleStringProperty tenProperty() {
        return ten;
    }

    public String getLop() {
        return lop.get();
    }

    public void setLop(String lop) {
        this.lop.set(lop);
    }

    public SimpleStringProperty lopProperty() {
        return lop;
    }

    public String getTenSach() {
        return tenSach.get();
    }

    public void setTenSach(String tenSach) {
        this.tenSach.set(tenSach);
    }

    public SimpleStringProperty tenSachProperty() {
        return tenSach;
    }

    public String getTrangThai() {
        return trangThai.get();
    }

    public void setTrangThai(String trangThai) {
        this.trangThai.set(trangThai);
    }

    public SimpleStringProperty trangThaiProperty() {
        return trangThai;
    }
}
