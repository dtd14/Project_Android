package com.example.project_android_ck.Quanlylaptop;

public class Laptop {

    private String maLapTop;
    private String tenLapTop;
    private int soluong;
    private Double gia;
    private String maNCC;

    public Laptop(String maLapTop, String tenLapTop, int soluong, Double gia, String maNCC) {
        this.maLapTop = maLapTop;
        this.tenLapTop = tenLapTop;
        this.soluong = soluong;
        this.gia = gia;
        this.maNCC = maNCC;
    }

    public Laptop() {
    }

    public String getMaLapTop() {
        return maLapTop;
    }

    public void setMaLapTop(String maLapTop) {
        this.maLapTop = maLapTop;
    }

    public String getTenLapTop() {
        return tenLapTop;
    }

    public void setTenLapTop(String tenLapTop) {
        this.tenLapTop = tenLapTop;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }
}
