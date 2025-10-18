package com.example.project_android_ck;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_android_ck.quanlydonhang.CustomAdapterQuanLyDonHang;
import com.example.project_android_ck.quanlydonhang.DonHang;

import java.util.ArrayList;

public class QuanLyDonHang extends AppCompatActivity {
    private SearchView searchView;
    private Toolbar toolbar;
    private ListView lvQuanLy;
    private Button btadd;
    private ArrayList<DonHang> listDonHang;
    private CustomAdapterQuanLyDonHang adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlydonhang_activity);
        init();



        // 1. Tạo danh sách mẫu
        listDonHang = new ArrayList<>();
        listDonHang.add(new DonHang("DH001", "Nguyễn Văn A", "Laptop Dell", 15000000, 2, "0123456789", "vana@example.com", "Hà Nội", 30000000, "Giao hàng nhanh", "01/10/2025"));
        listDonHang.add(new DonHang("DH002", "Trần Thị B", "Laptop Asus", 20000000, 1, "0987654321", "btran@example.com", "TP.HCM", 20000000, "Thanh toán online", "02/10/2025"));
        listDonHang.add(new DonHang("DH003", "Lê Văn C", "Laptop HP", 18000000, 1, "0912345678", "levanc@example.com", "Đà Nẵng", 18000000, "Giao hàng tiết kiệm", "03/10/2025"));
        listDonHang.add(new DonHang("DH004", "Phạm Thị D", "Laptop Lenovo", 22000000, 2, "0933456789", "phamthid@example.com", "Hải Phòng", 44000000, "Thanh toán khi nhận hàng", "04/10/2025"));
        listDonHang.add(new DonHang("DH005", "Ngô Văn E", "MacBook Pro", 35000000, 1, "0908765432", "ngovane@example.com", "Cần Thơ", 35000000, "Giao hàng nhanh", "05/10/2025"));

        // 2. Khởi tạo adapter
        adapter = new CustomAdapterQuanLyDonHang(this,R.layout.item_quanlydonhang,listDonHang);
        lvQuanLy.setAdapter(adapter);
        //btadd -> layout Thêm đơn hàng
        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // di chuyển giữa 2 màn hình
                Intent i = new Intent(QuanLyDonHang.this,ThemDonHang.class);
                startActivity(i);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });
    }
    //ánh xạ
    private void init(){
        lvQuanLy = findViewById(R.id.lv_view_qldh);
        btadd= findViewById(R.id.bt_add);
        searchView = findViewById(R.id.srv_timkiemdonhang);
    }
    private long themDonHang(){
        return 0;
    }

    //lấy thông tin từ listview
    private void getListView(){

    }
}
