package com.example.project_android_ck.Quanlylaptop;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_android_ck.Data.DAO;
import com.example.project_android_ck.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainLaptop extends Activity {
    private SearchView srv;
    private ListView lvlt;
    private ImageButton imgthemlt;
    private DAO dao;
    private ArrayList<Laptop> listlaptop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlylaptop_activity);

        dao = new DAO(this);
//        srv = findViewById(R.id.srv_qllt);
        lvlt = findViewById(R.id.lv_qllt);
        imgthemlt = findViewById(R.id.ibtn_themlt);
        listlaptop = dao.select_Laptop(); // hàm này bạn tự tạo trong DAO
        LaptopAdapter adapter = new LaptopAdapter(this, R.layout.item_quanlylaptop, listlaptop);
        lvlt.setAdapter(adapter);


        imgthemlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainLaptop.this,AddLaptop.class);
                startActivity(i);
            }
        });

    }
    protected void onResume() {
        super.onResume();
        // Tải lại dữ liệu mới nhất từ DB
        listlaptop = dao.select_Laptop();
        LaptopAdapter adapter = new LaptopAdapter(this, R.layout.item_quanlylaptop, listlaptop);
        lvlt.setAdapter(adapter);
    }
}
