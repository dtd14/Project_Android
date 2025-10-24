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

    public void themDonHang() {
        ContentValues values = new ContentValues();
    }


    //Crud nha cung cap
    @SuppressLint("Range")
    public List<NhaCungCap> getallnhacc() {
        List<NhaCungCap> ncc = new ArrayList<>();
        String querry = "Select * from NhaCungCap";
        Cursor c = db.rawQuery(querry, null);
        if (c.moveToFirst()) {
            do {
                ncc.add(new NhaCungCap(
                        c.getString(c.getColumnIndex("MaNCC")),
                        c.getString(c.getColumnIndex("TenNCC")),
                        c.getString(c.getColumnIndex("DiaChi")),
                        c.getString(c.getColumnIndex("DienThoai")),
                        c.getString(c.getColumnIndex("Email")),
                        c.getString(c.getColumnIndex("GhiChu"))
                ));
            } while (c.moveToNext());
        }
        c.close();

        return ncc;
    }

    public Cursor getChiTietNhaCungCap(String maNCC) {
        String query = "SELECT NCC.MaNCC, NCC.TenNCC, NCC.DiaChi, NCC.DienThoai, NCC.Email, " +
                "L.MaLaptop, L.TenLaptop , NCC.GhiChu " +
                "FROM NhaCungCap NCC " +
                "LEFT JOIN Laptop L ON NCC.MaNCC = L.MaNCC " +
                "WHERE NCC.MaNCC = ?";
        return db.rawQuery(query, new String[]{maNCC});
    }


    public long add_nhacc(NhaCungCap ncc) {
        ContentValues values = new ContentValues();
        values.put("MaNCC", ncc.getMancc());
        values.put("TenNCC", ncc.getTen());
        values.put("DiaChi", ncc.getDiaChi());
        values.put("DienThoai", ncc.getSoDienThoai());
        values.put("Email", ncc.getEmail());
        values.put("GhiChu", ncc.getGhiChu());
        return db.insert("NhaCungCap", null, values);
    }

    public int Update_ncc(NhaCungCap ncc) {
        ContentValues values = new ContentValues();
        values.put("MaNCC", ncc.getMancc());
        values.put("TenNCC", ncc.getTen());
        values.put("DiaChi", ncc.getDiaChi());
        values.put("DienThoai", ncc.getSoDienThoai());
        values.put("Email", ncc.getEmail());
        values.put("GhiChu", ncc.getGhiChu());
        return db.update("NhaCungCap", values, "MaNCC=?", new String[]{ncc.getMancc()});
    }

    public int delete_ncc(NhaCungCap ncc) {
        return db.delete("NhaCungCap", "MaNCC=?", new String[]{ncc.getMancc()});
    }

    public void backupBeforeUpdate(NhaCungCap ncc) {
        ContentValues values = new ContentValues();

        values.put("MaNCC", ncc.getMancc());
        values.put("TenNCC", ncc.getTen());
        values.put("DiaChi", ncc.getDiaChi());
        values.put("DienThoai", ncc.getSoDienThoai());
        values.put("Email", ncc.getEmail());
        values.put("GhiChu", ncc.getGhiChu());
        db.insertWithOnConflict("Backup_NhaCungCap", null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public boolean restoreNhaCungCap(String mancc) {
        boolean changed = false; // flag xem có gì thay đổi không
        Cursor cursor = db.rawQuery("SELECT * FROM Backup_NhaCungCap WHERE MaNCC=?", new String[]{mancc});
        if (cursor.moveToFirst()) {
            String ten = cursor.getString(cursor.getColumnIndexOrThrow("TenNCC"));
            String diaChi = cursor.getString(cursor.getColumnIndexOrThrow("DiaChi"));
            String dienThoai = cursor.getString(cursor.getColumnIndexOrThrow("DienThoai"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("Email"));
            String ghiChu = cursor.getString(cursor.getColumnIndexOrThrow("GhiChu"));

            // Lấy dữ liệu hiện tại từ bảng chính
            Cursor current = db.rawQuery("SELECT * FROM NhaCungCap WHERE MaNCC=?", new String[]{mancc});
            if (current.moveToFirst()) {
                String curTen = current.getString(current.getColumnIndexOrThrow("TenNCC"));
                String curDiaChi = current.getString(current.getColumnIndexOrThrow("DiaChi"));
                String curDienThoai = current.getString(current.getColumnIndexOrThrow("DienThoai"));
                String curEmail = current.getString(current.getColumnIndexOrThrow("Email"));
                String curGhiChu = current.getString(current.getColumnIndexOrThrow("GhiChu"));

                // So sánh dữ liệu
                if (!ten.equals(curTen) || !diaChi.equals(curDiaChi) || !dienThoai.equals(curDienThoai)
                        || !email.equals(curEmail) || !ghiChu.equals(curGhiChu)) {
                    changed = true;
                }
            }
//            else {
//                // Nếu chưa có trong bảng chính → coi như thay đổi
//                changed = true;
//            }
                current.close();

                if (changed) {
                    ContentValues values = new ContentValues();
                    values.put("MaNCC", mancc);
                    values.put("TenNCC", ten);
                    values.put("DiaChi", diaChi);
                    values.put("DienThoai", dienThoai);
                    values.put("Email", email);
                    values.put("GhiChu", ghiChu);

                    db.insertWithOnConflict("NhaCungCap", null, values, SQLiteDatabase.CONFLICT_REPLACE);
                    // Xóa backup nếu restore thành công
                    db.delete("Backup_NhaCungCap", "MaNCC=?", new String[]{mancc});
                }
            }
            cursor.close();
            return changed; // trả về true nếu có gì thay đổi
        }
    public List<NhaCungCap> searchNhaCungCap(String field, String keyword) {
        List<NhaCungCap> list = new ArrayList<>();
        keyword = "%" + keyword + "%";
        if(field.equals("Tất cả")){
            Cursor cursor = db.rawQuery(
                    "SELECT * FROM NhaCungCap WHERE MaNCC LIKE ? OR TenNCC LIKE ? OR DiaChi LIKE ?",
                    new String[]{keyword,keyword,keyword}
            );

            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") NhaCungCap ncc = new NhaCungCap(
                            cursor.getString(cursor.getColumnIndex("MaNCC")),
                            cursor.getString(cursor.getColumnIndex("TenNCC")),
                            cursor.getString(cursor.getColumnIndex("DiaChi")),
                            cursor.getString(cursor.getColumnIndex("DienThoai")),
                            cursor.getString(cursor.getColumnIndex("Email")),
                            cursor.getString(cursor.getColumnIndex("GhiChu"))
                    );
                    list.add(ncc);
                } while (cursor.moveToNext());
            }
            cursor.close();

        }else {

            Cursor cursor = db.rawQuery(
                    "SELECT * FROM NhaCungCap WHERE " + field + " LIKE ?",
                    new String[]{keyword}
            );

            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") NhaCungCap ncc = new NhaCungCap(
                            cursor.getString(cursor.getColumnIndex("MaNCC")),
                            cursor.getString(cursor.getColumnIndex("TenNCC")),
                            cursor.getString(cursor.getColumnIndex("DiaChi")),
                            cursor.getString(cursor.getColumnIndex("DienThoai")),
                            cursor.getString(cursor.getColumnIndex("Email")),
                            cursor.getString(cursor.getColumnIndex("GhiChu"))
                    );
                    list.add(ncc);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return list;
    }
    public boolean insertUser(String username, String password) {
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        long result = db.insert("Users", null, values);
        return result != -1;
    }

    public boolean checkLogin(String username, String password) {
        Cursor cursor = db.rawQuery("SELECT * FROM Users WHERE username=? AND password=?", new String[]{username, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }


}




