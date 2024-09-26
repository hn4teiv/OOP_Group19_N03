package com.mycompany.managelibrary.entity;

public class LoanReturn {
    private int ID;
    private String diaChi;
    private String tenSach;
    private String lop;
    private String trangThai;

    public LoanReturn(int ID, String diaChi, String tenSach, String lop, String trangThai) {
        this.ID = ID;
        this.diaChi = diaChi;
        this.tenSach = tenSach;
        this.lop = lop;
        this.trangThai = trangThai;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
