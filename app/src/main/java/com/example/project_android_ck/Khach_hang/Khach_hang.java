package com.example.project_android_ck.Khach_hang;

public class Khach_hang {
    public Khach_hang(String ma, String ten, String sdt, String email, String diachi) {
        this.ma = ma;
        this.ten = ten;
        this.sdt = sdt;
        this.email = email;
        this.diachi = diachi;
    }
    public Khach_hang()
    {}
    String ma, ten, sdt, email, diachi;

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
    public String toString() {
        return ten;  // hiển thị tên trong AutoComplete
    }
}
