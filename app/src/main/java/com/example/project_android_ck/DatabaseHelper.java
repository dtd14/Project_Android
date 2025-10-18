package com.example.project_android_ck;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "LaptopDB";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "Laptop";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_BRAND = "brand";
    private static final String COL_PRICE = "price";
    private static final String COL_QUANTITY = "quantity";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT, "
                + COL_BRAND + " TEXT, "
                + COL_PRICE + " REAL, "
                + COL_QUANTITY + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Thêm laptop
    public void addLaptop(String name, String brand, double price, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_BRAND, brand);
        values.put(COL_PRICE, price);
        values.put(COL_QUANTITY, quantity);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Lấy danh sách laptop
    public ArrayList<Laptop> getAllLaptops() {
        ArrayList<Laptop> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                list.add(new Laptop(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getDouble(3),
                        cursor.getInt(4)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    // Xóa laptop
    public void deleteLaptop(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Cập nhật laptop
    public void updateLaptop(int id, String name, String brand, double price, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_BRAND, brand);
        values.put(COL_PRICE, price);
        values.put(COL_QUANTITY, quantity);
        db.update(TABLE_NAME, values, COL_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Sửa Laptop
    public int updateLaptop(Laptop laptop) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", laptop.name);
        values.put("brand", laptop.brand);
        values.put("price", laptop.price);
        values.put("quantity", laptop.quantity);

        // cập nhật theo id
        return db.update("laptop", values, "id = ?", new String[]{String.valueOf(laptop.id)});
    }
}
