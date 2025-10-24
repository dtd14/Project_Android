package com.example.project_android_ck.Donhang.quanlydonhang.Themdonhang;

public class DonHang {
    private String maDH;
    private String maKH;
    private String ngayLap;
    private double tongTien;
    private String moTa;

    public DonHang() {}

    public DonHang(String maDH, String maKH, String ngayLap, double tongTien, String moTa) {
        this.maDH = maDH;
        this.maKH = maKH;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.moTa = moTa;
    }

    public String getMaDH() { return maDH; }
    public String getMaKH() { return maKH; }
    public String getNgayLap() { return ngayLap; }
    public double getTongTien() { return tongTien; }
    public String getMoTa() { return moTa; }

    public void setMaDH(String maDH) { this.maDH = maDH; }
    public void setMaKH(String maKH) { this.maKH = maKH; }
    public void setNgayLap(String ngayLap) { this.ngayLap = ngayLap; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
}
