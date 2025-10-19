package com.example.project_android_ck.Data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

<<<<<<< HEAD
import com.example.project_android_ck.Donhang.quanlydonhang.Themdonhang.DonHang;
import com.example.project_android_ck.Donhang.quanlydonhang.Quanlydonhang.DonHangFull;

import java.util.ArrayList;
=======
import com.example.project_android_ck.NhaCungCap.NhaCungCap;

import java.util.ArrayList;
import java.util.List;
>>>>>>> e4fb54af6914fb8d7ea40bcb64714b840537a4d6

public class DAO {
    private SQLiteDatabase db;
    private DataBaseHelper data;

    public DAO(Context context) {
        data = new DataBaseHelper(context);
        db = data.getWritableDatabase(); // mở database thật
    }


<<<<<<< HEAD
                                        //Quản lý đơn hàng
    //Thêm đơn hàng
    public void themDonHang(DonHang dh ){
        ContentValues values = new ContentValues();
        values.put("MaDH",dh.getMaDH());
        values.put("MaKH",dh.getMaKH());
        values.put("NgayLap",dh.getNgayDat());
        values.put("TongTien",dh.getTongTien());
        values.put("MoTa",dh.getMoTa());
        db.insert("DonHang",null,values);
    }
    //Lấy thông tin đơn hàng
    public ArrayList<DonHangFull> selectThongTinDonHang(){
        ArrayList<DonHangFull>  dhFull = new ArrayList<>();
        String sql = "SELECT dh.MaDH, kh.TenKH, lt.TenLaptop, lt.Gia, " +
                "ctdh.SoLuong, kh.SDT, kh.Email, kh.DiaChi, dh.TongTien, dh.MoTa, dh.NgayLap " +
                "FROM DonHang dh " +
                "JOIN KhachHang kh ON dh.MaKH = kh.MaKH " +
                "JOIN ChiTietDonHang ctdh ON dh.MaDH = ctdh.MaDH " +
                "JOIN Laptop lt ON ctdh.MaLaptop = lt.MaLaptop";
        Cursor c = db.rawQuery(sql,null);
        // con trỏ đến dữ liệu trong danh sách
        c.moveToFirst();
        while (c.isAfterLast() == false){
            String maHD,hoTen,tenLapTop,soDienThoai,email,diaChi,moTa,ngayLap;
            Double gia,tongTien;
            int soLuong;
            maHD = c.getString(0);
            hoTen = c.getString(1);
            tenLapTop = c.getString(2);
            gia = c.getDouble(3);
            soLuong = c.getInt(4);
            soDienThoai = c.getString(5);
            email = c.getString(6);
            diaChi = c.getString(7);
            tongTien = c.getDouble(8);
            moTa = c.getString(9);
            ngayLap = c.getString(10);
            dhFull.add(new DonHangFull(maHD,hoTen,tenLapTop,gia,soLuong,soDienThoai,email,diaChi,tongTien,moTa,ngayLap));
            c.moveToNext();
        }
        c.close();
        return dhFull;
    }
=======
    //Crud nha cung cap
    @SuppressLint("Range")
    public List<NhaCungCap> getallnhacc(){
        List<NhaCungCap> ncc = new ArrayList<>();
        String querry = "Select * from NhaCungCap";
        Cursor c = db.rawQuery(querry,null);
        if(c.moveToFirst()){
            do{
                ncc.add(new NhaCungCap(
                        c.getString(c.getColumnIndex("MaNCC")),
                        c.getString(c.getColumnIndex("TenNCC")),
                        c.getString(c.getColumnIndex("DiaChi")),
                        c.getString(c.getColumnIndex("DienThoai")),
                        c.getString(c.getColumnIndex("Email")),
                        c.getString(c.getColumnIndex("GhiChu"))
                ));
            }while(c.moveToNext());
        }
        return ncc;
    }
    public long addnhacc(NhaCungCap ncc){
        ContentValues values = new ContentValues();
        values.put("MaNCC",ncc.getMancc());
        values.put("TenNCC",ncc.getTen());
        values.put("DiaChi",ncc.getDiaChi());
        values.put("DienThoai",ncc.getSoDienThoai());
        values.put("Email",ncc.getEmail());
        values.put("GhiChu",ncc.getGhiChu());
        return db.insert("NhaCungCap",null,values);
    }

>>>>>>> e4fb54af6914fb8d7ea40bcb64714b840537a4d6
}
