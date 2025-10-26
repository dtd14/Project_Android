package com.example.project_android_ck.Donhang.quanlydonhang.Chitietdonhang;

public class ChiTiet {
    private String maDH;
    private String maLaptop;
    private int soLuong;
    private double donGia;

    public ChiTiet(String maDH, String maLaptop, int soLuong, double donGia) {
        this.maDH = maDH;
        this.maLaptop = maLaptop;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getMaDH() { return maDH; }
    public String getMaLaptop() { return maLaptop; }
    public int getSoLuong() { return soLuong; }
    public double getDonGia() { return donGia; }

    public void setMaDH(String maDH) { this.maDH = maDH; }
    public void setMaLaptop(String maLaptop) { this.maLaptop = maLaptop; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public void setDonGia(double donGia) { this.donGia = donGia; }
}
