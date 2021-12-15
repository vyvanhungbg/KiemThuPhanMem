package com.ktpm.BankingDomainApplicationTesting.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

public class HoaDon {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHoaDon;
    private String nguoiNhan;
    private String nguoiGui;
    private String soTien;
    private String time;
    private String ghiChu;


    public HoaDon( String nguoiNhan, String nguoiGui, String soTien, String time, String ghiChu) {
        this.nguoiNhan = nguoiNhan;
        this.nguoiGui = nguoiGui;
        this.soTien = soTien;
        this.time = time;
        this.ghiChu = ghiChu;
    }

    public Long getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(Long idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getNguoiNhan() {
        return nguoiNhan;
    }

    public void setNguoiNhan(String nguoiNhan) {
        this.nguoiNhan = nguoiNhan;
    }

    public String getNguoiGui() {
        return nguoiGui;
    }

    public void setNguoiGui(String nguoiGui) {
        this.nguoiGui = nguoiGui;
    }

    public String getSoTien() {
        return soTien;
    }

    public void setSoTien(String soTien) {
        this.soTien = soTien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
