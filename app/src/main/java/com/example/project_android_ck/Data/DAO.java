package com.example.project_android_ck.Data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project_android_ck.NhaCungCap.NhaCungCap;

import java.util.ArrayList;
import java.util.List;

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

}
