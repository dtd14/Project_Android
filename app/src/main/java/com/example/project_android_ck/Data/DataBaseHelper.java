package com.example.project_android_ck.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "QLBanLapTop.db";
    public static final int DB_VERSION = 3;

    public DataBaseHelper(@Nullable Context context) {
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Bật khóa ngoại
        // Nếu không bật lên thì sqlite sẽ bỏ qua các ràng buộc Khóa
        db.execSQL("PRAGMA foreign_keys = ON;");

        //  Bảng NhaCungCap
        db.execSQL("CREATE TABLE IF NOT EXISTS NhaCungCap (" +
                "MaNCC TEXT PRIMARY KEY, " +
                "TenNCC TEXT NOT NULL, " +
                "DiaChi TEXT, " +
                "DienThoai TEXT, " +
                "Email TEXT,"+
                "GhiChu TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Backup_NhaCungCap  (" +
                "MaNCC TEXT PRIMARY KEY, " +
                "TenNCC TEXT NOT NULL, " +
                "DiaChi TEXT, " +
                "DienThoai TEXT, " +
                "Email TEXT,"+
                "GhiChu TEXT)");

        //  Bảng Laptop
        db.execSQL("CREATE TABLE IF NOT EXISTS Laptop (" +
                "MaLaptop TEXT PRIMARY KEY, " +
                "TenLaptop TEXT NOT NULL, " +
                "Gia REAL NOT NULL, " +
                "SoLuong INTEGER NOT NULL, " +
                "MaNCC TEXT, " +
                "FOREIGN KEY (MaNCC) REFERENCES NhaCungCap(MaNCC) ON DELETE CASCADE)");

        //  Bảng KhachHang
        db.execSQL("CREATE TABLE IF NOT EXISTS KhachHang (" +
                "MaKH TEXT PRIMARY KEY, " +
                "TenKH TEXT NOT NULL, " +
                "SDT TEXT, " +
                "Email TEXT, " +
                "DiaChi TEXT)");

        //  Bảng DonHang
        db.execSQL("CREATE TABLE IF NOT EXISTS DonHang (" +
                "MaDH TEXT PRIMARY KEY, " +
                "NgayLap TEXT NOT NULL, " +
                "MaKH TEXT, " +
                "TongTien REAL DEFAULT 0, " +
                "MoTa TEXT, " +
                "FOREIGN KEY (MaKH) REFERENCES KhachHang(MaKH) ON DELETE CASCADE)");

        //  Bảng ChiTietDonHang
        db.execSQL("CREATE TABLE IF NOT EXISTS ChiTietDonHang (" +
                "MaDH TEXT, " +
                "MaLaptop TEXT, " +
                "SoLuong INTEGER NOT NULL, " +
                "DonGia REAL NOT NULL, " +
                "PRIMARY KEY (MaDH, MaLaptop), " +
                "FOREIGN KEY (MaDH) REFERENCES DonHang(MaDH) ON DELETE CASCADE, " +
                "FOREIGN KEY (MaLaptop) REFERENCES Laptop(MaLaptop) ON DELETE CASCADE)");
        // Bảng login
        db.execSQL("CREATE TABLE IF NOT EXISTS Users(username TEXT PRIMARY KEY, password TEXT)");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa các bảng cũ nếu có
        db.execSQL("DROP TABLE IF EXISTS ChiTietDonHang");
        db.execSQL("DROP TABLE IF EXISTS DonHang");
        db.execSQL("DROP TABLE IF EXISTS Laptop");
        db.execSQL("DROP TABLE IF EXISTS NhaCungCap");
        db.execSQL("DROP TABLE IF EXISTS KhachHang");
        db.execSQL("DROP TABLE IF EXISTS Backup_NhaCungCap");
        db.execSQL("Drop table if Exists Users");
        // Tạo lại bảng
        onCreate(db);
    }
}
