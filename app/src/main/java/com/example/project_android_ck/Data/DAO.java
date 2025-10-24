package com.example.project_android_ck.Data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.project_android_ck.Khach_hang.Khach_hang;
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
        c.close();

        return ncc;
    }
    public long add_nhacc(NhaCungCap ncc){
        ContentValues values = new ContentValues();
        values.put("MaNCC",ncc.getMancc());
        values.put("TenNCC",ncc.getTen());
        values.put("DiaChi",ncc.getDiaChi());
        values.put("DienThoai",ncc.getSoDienThoai());
        values.put("Email",ncc.getEmail());
        values.put("GhiChu",ncc.getGhiChu());
        return db.insert("NhaCungCap",null,values);
    }
    public int Update_ncc(NhaCungCap ncc){
        ContentValues values = new ContentValues();
        values.put("MaNCC",ncc.getMancc());
        values.put("TenNCC",ncc.getTen());
        values.put("DiaChi",ncc.getDiaChi());
        values.put("DienThoai",ncc.getSoDienThoai());
        values.put("Email",ncc.getEmail());
        values.put("GhiChu",ncc.getGhiChu());
        return db.update("NhaCungCap",values,"MaNCC=?",new String[]{ncc.getMancc()});
    }
    public int delete_ncc(NhaCungCap ncc){
        return db.delete("NhaCungCap","MaNCC=?",new String[]{ncc.getMancc()});
    }


    //KHACH HANG
    public long add_khachhang(Khach_hang kh)
    {
        long result;
        ContentValues values = new ContentValues();
        values.put("MaKH",kh.getMa());
        values.put("TenKH",kh.getTen());
        values.put("SDT",kh.getSdt());
        values.put("Email",kh.getEmail());
        values.put("DiaChi",kh.getDiachi());
        result = db.insert("KhachHang",null,values);
        return result;
//        if(result<=0)
//        {
//            Toast.makeText(, "Thêm không thành công!", Toast.LENGTH_SHORT).show();
//        }
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
