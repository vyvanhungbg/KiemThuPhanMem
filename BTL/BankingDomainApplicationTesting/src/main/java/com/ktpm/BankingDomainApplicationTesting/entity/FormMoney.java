package com.ktpm.BankingDomainApplicationTesting.entity;

public class FormMoney {
    String nganHang;
    String sdtNguoiNhan;
    String soTien;
    String thongDiep;


    public FormMoney(String nganHang, String sdtNguoiNhan, String soTien, String thongDiep) {
        this.nganHang = nganHang;
        this.sdtNguoiNhan = sdtNguoiNhan;
        this.soTien = soTien;
        this.thongDiep = thongDiep;
    }

    public FormMoney() {
    }

    public String getNganHang() {
        return nganHang;
    }

    public void setNganHang(String nganHang) {
        this.nganHang = nganHang;
    }

    public String getSdtNguoiNhan() {
        return sdtNguoiNhan;
    }

    public void setSdtNguoiNhan(String sdtNguoiNhan) {
        this.sdtNguoiNhan = sdtNguoiNhan;
    }

    public String getSoTien() {
        return soTien;
    }

    public void setSoTien(String soTien) {
        this.soTien = soTien;
    }

    public String getThongDiep() {
        return thongDiep;
    }

    public void setThongDiep(String thongDiep) {
        this.thongDiep = thongDiep;
    }
}
