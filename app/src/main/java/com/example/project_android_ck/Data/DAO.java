package com.example.project_android_ck.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project_android_ck.Khach_hang.Khach_hang;

import java.util.ArrayList;

public class DAO {
    private SQLiteDatabase db;
    private DataBaseHelper data;

    public DAO(Context context) {
        data = new DataBaseHelper(context);
        db = data.getWritableDatabase(); // mở database thật
    }
    public void themDonHang(){
        ContentValues values = new ContentValues();
    }

    //KHACH HANG

    public void add_khachhang(Khach_hang kh)
    {
        ContentValues values = new ContentValues();
        values.put("MaKH",kh.getMa());
        values.put("TenKH",kh.getTen());
        values.put("SDT",kh.getSdt());
        values.put("Email",kh.getEmail());
        values.put("DiaChi",kh.getDiachi());
        db.insert("KhachHang",null,values);
    }
    public void update_khachhang(Khach_hang kh)
    {
        ContentValues values = new ContentValues();
        values.put("MaKH",kh.getMa());
        values.put("TenKH",kh.getTen());
        values.put("SDT",kh.getSdt());
        values.put("Email",kh.getEmail());
        values.put("DiaChi",kh.getDiachi());
        db.update("KhachHang",values,"MaKH = ?", new String[]{kh.getMa()});
    }
    public int delete_khachhang(Khach_hang kh)
    {
        return db.delete("KhachHang","Makh = ?", new String[]{kh.getMa()});
    }
    public ArrayList<Khach_hang> select_khachhang()
    {
        ArrayList<Khach_hang> arrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from KhachHang",null);
        cursor.moveToFirst();
        while (cursor.isAfterLast()==false)
        {
            String ma,ten,sdt,email,diachi;
            ma = cursor.getString(0);
            ten = cursor.getString(1);
            sdt = cursor.getString(2);
            email = cursor.getString(3);
            diachi = cursor.getString(4);
            arrayList.add(new Khach_hang(ma,ten,sdt,email,diachi));
            cursor.moveToNext();
        }
        cursor.close();
        return arrayList;
    }
}
