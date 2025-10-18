package com.example.project_android_ck.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DAO {
    private SQLiteDatabase db;
    private DataBaseHelper data;

    public DAO(Context context) {
        data = new DataBaseHelper(context);
        db = data.getWritableDatabase(); // mở database thật
    }
}
