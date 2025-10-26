package com.example.project_android_ck.Data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project_android_ck.Donhang.quanlydonhang.Chitietdonhang.ChiTiet;
import com.example.project_android_ck.Donhang.quanlydonhang.Quanlydonhang.DonHangFull;
import com.example.project_android_ck.Donhang.quanlydonhang.Themdonhang.DonHang;
import com.example.project_android_ck.Khach_hang.Khach_hang;
import com.example.project_android_ck.Quanlylaptop.Laptop;
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
    public long themDonHang(DonHang dh ){
        ContentValues values = new ContentValues();
        values.put("MaDH",dh.getMaDH());
        values.put("MaKH",dh.getMaKH());
        values.put("NgayLap",dh.getNgayLap());
        values.put("TongTien",dh.getTongTien());
        values.put("MoTa",dh.getMoTa());
        return db.insert("DonHang",null,values);
    }
    //Thêm chi tiết đơn hàng
    public void themChiTietDonHang(String MaDH,String MaLapTop,int SoLuong,Double DonGia){
        ContentValues values = new ContentValues();
        values.put("MaDH",MaDH);
        values.put("MaLaptop",MaLapTop);
        values.put("SoLuong",SoLuong);
        values.put("DonGia",DonGia);
        db.insert("ChiTietDonHang",null,values);
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

    //Update đơn hàng
    public int updateDonHang(DonHang dh){
        ContentValues values = new ContentValues();
        values.put("MaDH",dh.getMaDH());
        values.put("NgayLap",dh.getNgayLap());
        values.put("MaKH",dh.getMaKH());
        values.put("TongTien",dh.getTongTien());
        values.put("MoTa",dh.getMoTa());
        return db.update("DonHang",values,"MaDH = ?",new String[]{dh.getMaDH()});
    }
    public int updateChiTietDonHang(ChiTiet ct){
        ContentValues values = new ContentValues();
        values.put("MaLaptop",ct.getMaLaptop());
        values.put("SoLuong",ct.getSoLuong());
        values.put("DonGia",ct.getDonGia());
        return db.update("ChiTietDonHang",values,"MaDH = ? and MaLaptop = ?",new String[]{ct.getMaDH(),ct.getMaLaptop()});
    }


    //Xóa Đơn Hàng
    public int xoaDonHang(String MaDH){
        return db.delete("DonHang","MaDH = ?",new String[]{MaDH});
    }

    // Xóa chi tiet don hang
    public int xoaChiTietDonHang(String MaDH){
        return db.delete("ChiTietDonHang","MaDH = ?",new String[]{MaDH});
    }
    /// Lấy mãkhacschs hàng theo teen
    public String layMaKhachHangTheoTen(String tenKH) {
        Cursor c = db.rawQuery("SELECT MaKH FROM KhachHang WHERE TenKH = ?", new String[]{tenKH});
        if (c.moveToFirst()) {
            String ma = c.getString(0);
            c.close();
            return ma;
        }
        c.close();
        return null;
    }
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



    ///  QUẢN LÝ LAPTOP ///
    /// THÊM LAPTOP
    public long themLapTop(Laptop lt){
        ContentValues values = new ContentValues();
        values.put("MaLaptop",lt.getMaLapTop());
        values.put("TenLaptop",lt.getTenLapTop());
        values.put("Gia",lt.getGia());
        values.put("SoLuong",lt.getSoluong());
        values.put("MaNCC",lt.getMaNCC());
        return db.insert("Laptop",null,values);
    }
    /// SỬA LAPTOP
    /// XÓA LAPTOP
    public int delete_laptop(Laptop lt) {
        return db.delete("Laptop", "MaLaptop=?", new String[]{lt.getMaLapTop()});
    }
    /// Lấy laptop
     public ArrayList<String> getDanhSachMaNCC() {
             ArrayList<String> dsMa = new ArrayList<>();
             Cursor c = db.rawQuery("SELECT MaNCC FROM NhaCungCap", null);
            if (c.moveToFirst()) {
                 do {
                     dsMa.add(c.getString(0));
                 } while (c.moveToNext());
           }
            c.close();
             return dsMa;
         }

    public ArrayList<Laptop> select_Laptop(){
        ArrayList<Laptop> dsLapTop = new ArrayList<>();
        Cursor c = db.rawQuery("Select * from Laptop",null);
        c.moveToFirst();
        while (c.isAfterLast() == false){
            String maLapTop,tenLapTop,maNCC;
            Double gia;
            int soLuong;
            maLapTop = c.getString(0);
            tenLapTop = c.getString(1);
            gia = c.getDouble(2);
            soLuong = c.getInt(3);
            maNCC = c.getString(4);
            dsLapTop.add(new Laptop(maLapTop,tenLapTop,soLuong,gia,maNCC));
            c.moveToNext();
        }
        c.close();
        return dsLapTop;
    }
    // tim kiem
    public ArrayList<Laptop> search_laptop(String keywork){
         ArrayList<Laptop> lt = new ArrayList<>();
         keywork = "%" + keywork + "%";
         String query = "SELECT * FROM  Laptop WHERE MaLaptop LIKE ? OR TenLaptop LIKE ?  ";
         Cursor c = db.rawQuery(query,new String[]{keywork,keywork});
         if(c.moveToFirst()){
             do {
                 lt.add(new Laptop(
                         c.getString(0),
                         c.getString(1),
                         c.getInt(2),
                         c.getDouble(3),
                         c.getString(4)
                 ));

             }while(c.moveToNext());
         }
         c.close();
         return lt;
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





