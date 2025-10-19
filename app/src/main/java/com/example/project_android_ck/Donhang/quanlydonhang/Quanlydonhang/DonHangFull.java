package com.example.project_android_ck.Donhang.quanlydonhang.Quanlydonhang;

public class DonHangFull {
    private String hoTen;
    private String maDH;
    private String tenLapTop;
    private double gia;
    private int soLuong;
    private String soDienThoai;
    private String email;
    private String diaChi;
    private double tongTien;
    private String moTa;

    private String ngayLap;

    public DonHangFull(String maDH, String hoTen, String tenLapTop, double gia, int soLuong, String soDienThoai, String email, String diaChi, double tongTien, String moTa, String ngayLap) {
        this.maDH = maDH;
        this.hoTen = hoTen;
        this.tenLapTop = tenLapTop;
        this.gia = gia;
        this.soLuong = soLuong;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diaChi = diaChi;
        this.tongTien = tongTien;
        this.moTa = moTa;
        this.ngayLap = ngayLap;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getMaDH() {
        return maDH;
    }

    public void setMaDH(String maDH) {
        this.maDH = maDH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getTenLapTop() {
        return tenLapTop;
    }

    public void setSanPham(String sanPham) {
        this.tenLapTop = sanPham;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
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
