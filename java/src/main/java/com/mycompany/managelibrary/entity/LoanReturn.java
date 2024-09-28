package com.mycompany.managelibrary.entity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "LoanReturn")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoanReturn implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement(name = "Id")
    private int id;

    @XmlElement(name = "DiaChi")
    private String diaChi;

    @XmlElement(name = "Ten")
    private String ten;

    @XmlElement(name = "Lop")
    private String lop;

    @XmlElement(name = "TenSach")
    private String tenSach;

    @XmlElement(name = "TrangThai")
    private String trangThai; // "Đã trả" or "Chưa trả"

    // Constructor mặc định
    public LoanReturn() {
    }

    // Constructor với 5 tham số
    public LoanReturn(int id, String diaChi, String ten, String lop, String tenSach) {
        this.id = id;
        this.diaChi = diaChi;
        this.ten = ten;
        this.lop = lop;
        this.tenSach = tenSach;
        this.trangThai = "Chưa trả"; // Giá trị mặc định
    }

    // Constructor với 6 tham số
    public LoanReturn(int id, String diaChi, String ten, String lop, String tenSach, String trangThai) {
        this.id = id;
        this.diaChi = diaChi;
        this.ten = ten;
        this.lop = lop;
        this.tenSach = tenSach;
        this.trangThai = trangThai;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getTen() {
        return ten;
    }

    public String getLop() {
        return lop;
    }

    public String getTenSach() {
        return tenSach;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
