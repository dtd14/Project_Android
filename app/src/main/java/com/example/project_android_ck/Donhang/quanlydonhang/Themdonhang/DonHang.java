package com.example.project_android_ck.Donhang.quanlydonhang.Themdonhang;

public class DonHang {
    private String maDH;
    private String maKH; // Khóa ngoại tới bảng khách hàng
    private String maSP; // Khóa ngoại tới bảng sản phẩm
    private String ngayDat;
    private int soLuong;
    private double tongTien;
    private String moTa;

    public DonHang() {
    }

    public DonHang(String maDH, String maKH, String maSP, String ngayDat, int soLuong, double tongTien, String moTa) {
        this.maDH = maDH;
        this.maKH = maKH;
        this.maSP = maSP;
        this.ngayDat = ngayDat;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.moTa = moTa;
    }

    public String getMaDH() {
        return maDH;
    }

    public void setMaDH(String maDH) {
        this.maDH = maDH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
