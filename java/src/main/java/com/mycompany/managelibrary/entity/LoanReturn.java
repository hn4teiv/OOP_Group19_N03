package com.mycompany.managelibrary.entity;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;


public class LoanReturn {
    private final IntegerProperty id;
    private final StringProperty diaChi;
    private final StringProperty ten;
    private final StringProperty lop;
    private final StringProperty tenSach;
    private final StringProperty trangThai;

    public LoanReturn() {
        this.id = new SimpleIntegerProperty();
        this.diaChi = new SimpleStringProperty();
        this.ten = new SimpleStringProperty();
        this.lop = new SimpleStringProperty();
        this.tenSach = new SimpleStringProperty();
        this.trangThai = new SimpleStringProperty();
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getDiaChi() {
        return diaChi.get();
    }

    public void setDiaChi(String diaChi) {
        this.diaChi.set(diaChi);
    }

    public StringProperty diaChiProperty() {
        return diaChi;
    }

    public String getTen() {
        return ten.get();
    }

    public void setTen(String ten) {
        this.ten.set(ten);
    }

    public StringProperty tenProperty() {
        return ten;
    }

    public String getLop() {
        return lop.get();
    }

    public void setLop(String lop) {
        this.lop.set(lop);
    }

    public StringProperty lopProperty() {
        return lop;
    }

    public String getTenSach() {
        return tenSach.get();
    }

    public void setTenSach(String tenSach) {
        this.tenSach.set(tenSach);
    }

    public StringProperty tenSachProperty() {
        return tenSach;
    }

    public String getTrangThai() {
        return trangThai.get();
    }

    public void setTrangThai(String trangThai) {
        this.trangThai.set(trangThai);
    }

    public StringProperty trangThaiProperty() {
        return trangThai;
    }
}
