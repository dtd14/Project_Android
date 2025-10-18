package com.example.project_android_ck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class quanlykhachhang extends Activity {
    public ArrayList<Khach_hang> arr;
    public Custom_adapter_qlkh adap;
    public Khach_hang khachHang = new Khach_hang();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlykhachhang);
        ImageButton them;
        RecyclerView rv_qlkh = findViewById(R.id.rv_qlkh);

        them = findViewById(R.id.ibtn_themkh);
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent (quanlykhachhang.this,chitiet_qlkh.class);
                startActivity(it);
            }
        });

    }
}
