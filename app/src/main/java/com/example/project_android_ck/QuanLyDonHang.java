package com.example.project_android_ck;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private RecyclerView recyclerView;
    private Button btadd;
    private ArrayList<DonHang> listDonHang;
    private CustomAdapterQuanLyDonHang adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlydonhang_activity);
        //ánh xạ
        recyclerView = findViewById(R.id.rc_view_qldh);
        btadd= findViewById(R.id.bt_add);
        searchView = findViewById(R.id.srv_timkiemdonhang);



        // 1. Tạo danh sách mẫu
        listDonHang = new ArrayList<>();
        listDonHang.add(new DonHang("DH001", "Nguyễn Văn A", "Laptop Dell", 15000000, 2, "0123456789", "vana@example.com", "Hà Nội", 30000000, "Giao hàng nhanh", "01/10/2025"));
        listDonHang.add(new DonHang("DH002", "Trần Thị B", "Laptop Asus", 20000000, 1, "0987654321", "btran@example.com", "TP.HCM", 20000000, "Thanh toán online", "02/10/2025"));
        listDonHang.add(new DonHang("DH003", "Lê Văn C", "Laptop HP", 18000000, 1, "0912345678", "levanc@example.com", "Đà Nẵng", 18000000, "Giao hàng tiết kiệm", "03/10/2025"));
        listDonHang.add(new DonHang("DH004", "Phạm Thị D", "Laptop Lenovo", 22000000, 2, "0933456789", "phamthid@example.com", "Hải Phòng", 44000000, "Thanh toán khi nhận hàng", "04/10/2025"));
        listDonHang.add(new DonHang("DH005", "Ngô Văn E", "MacBook Pro", 35000000, 1, "0908765432", "ngovane@example.com", "Cần Thơ", 35000000, "Giao hàng nhanh", "05/10/2025"));
        listDonHang.add(new DonHang("DH006", "Hoàng Thị F", "Laptop Acer", 15000000, 3, "0922334455", "hoangthif@example.com", "Nha Trang", 45000000, "Thanh toán online", "06/10/2025"));
        listDonHang.add(new DonHang("DH007", "Đỗ Văn G", "Laptop MSI", 25000000, 1, "0944556677", "dovang@example.com", "Bình Dương", 25000000, "Giao hàng tiết kiệm", "07/10/2025"));
        listDonHang.add(new DonHang("DH008", "Vũ Thị H", "Laptop Dell XPS", 30000000, 2, "0966778899", "vuthih@example.com", "Vũng Tàu", 60000000, "Thanh toán khi nhận hàng", "08/10/2025"));
        listDonHang.add(new DonHang("DH009", "Bùi Văn I", "Laptop Asus ROG", 28000000, 1, "0977889900", "buivani@example.com", "Quảng Ninh", 28000000, "Giao hàng nhanh", "09/10/2025"));
        listDonHang.add(new DonHang("DH010", "Trương Thị K", "MacBook Air", 27000000, 1, "0988990011", "truongthik@example.com", "Hà Nội", 27000000, "Thanh toán online", "10/10/2025"));
        listDonHang.add(new DonHang("DH011", "Lý Văn L", "Laptop Lenovo ThinkPad", 23000000, 2, "0999001122", "lyvanl@example.com", "TP.HCM", 46000000, "Giao hàng tiết kiệm", "11/10/2025"));
        listDonHang.add(new DonHang("DH012", "Nguyễn Thị M", "Laptop HP Spectre", 32000000, 1, "0901122334", "nguyenthim@example.com", "Đà Lạt", 32000000, "Thanh toán online", "12/10/2025"));
        // 2. Khởi tạo adapter
        adapter = new CustomAdapterQuanLyDonHang(this,listDonHang);

        // 3. Gắn layout manager + adapter vào RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        //btadd -> layout Thêm đơn hàng
        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // di chuyển giữa 2 màn hình
                Intent i = new Intent(QuanLyDonHang.this,ThemDonHang.class);
                startActivity(i);
            }
        });
    }
}
