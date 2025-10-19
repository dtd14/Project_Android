package com.example.project_android_ck.NhaCungCap;

public class NhaCungCap {
    private String Mancc;
    private String ten;
    private String diaChi;
    private String soDienThoai;
    private String email;
    private String ghiChu;

    public NhaCungCap(String Mancc,String ten, String diaChi, String soDienThoai, String email, String ghiChu) {
        this.Mancc = Mancc;
        this.ten = ten;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.ghiChu = ghiChu;
    }

    public String getMancc() {
        return Mancc;
    }

    public void setMancc(String mancc) {
        Mancc = mancc;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
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

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
