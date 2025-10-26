package com.example.project_android_ck.Donhang.quanlydonhang.Quanlydonhang;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.Nullable;

import com.example.project_android_ck.Data.DAO;
import com.example.project_android_ck.Data.DataBaseHelper;
import com.example.project_android_ck.Donhang.quanlydonhang.Chitietdonhang.ChiTietDonHang;

import com.example.project_android_ck.Donhang.quanlydonhang.Themdonhang.DonHang;
import com.example.project_android_ck.Donhang.quanlydonhang.Themdonhang.ThemDonHang;
import com.example.project_android_ck.NhaCungCap.QuanLiNhaCungCap;
import com.example.project_android_ck.R;

import java.util.ArrayList;

public class QuanLyDonHang extends AppCompatActivity {
    private SearchView searchView;
    private Toolbar toolbar;
    private ListView lvQuanLy;
    private Button btadd,btout;
    private ArrayList<DonHangFull> listDonHang;
    private CustomAdapterQuanLyDonHang adapter;
    private ArrayList<DonHang> danhsachdonhang = new ArrayList<>();

    private DAO dao;
    private DataBaseHelper data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlydonhang_activity);
        init();
        dao = new DAO(this);
        listViewThongTinDonHang();
        // 2. Khởi tạo adapter
        //btadd -> layout Thêm đơn hàng
        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // di chuyển giữa 2 màn hình
                Intent i = new Intent(QuanLyDonHang.this, ThemDonHang.class);
                startActivity(i);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String keyword) {
                adapter.getFilter().filter(keyword);
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }
        });

        /// Bảng chi tiết đơn hàng
        lvQuanLy.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(QuanLyDonHang.this, ChiTietDonHang.class);
                i.putExtra("MaDH",listDonHang.get(position).getMaDH());
                startActivity(i);
                return true;
            }
        });
        btout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ab = new AlertDialog.Builder(QuanLyDonHang.this);
                ab.setTitle("Quay Lại");
                ab.setMessage("Bạn có muốn quay lại menu không?");
                ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ab.show();
            }
        });


    }
    //out menu
    private void init(){
        lvQuanLy = findViewById(R.id.lv_view_qldh);
        btadd= findViewById(R.id.bt_add);
        searchView = findViewById(R.id.srv_timkiemdonhang);
        btout = findViewById(R.id.bt_out);
    }
    public void listViewThongTinDonHang(){
        listDonHang =dao.selectThongTinDonHang();
        adapter = new CustomAdapterQuanLyDonHang(this,R.layout.item_quanlydonhang,listDonHang);
        lvQuanLy.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    // Mỗi khi quay lại layout này thì load lai listview
    @Override
    protected void onResume() {
        super.onResume();
        listViewThongTinDonHang(); // Mỗi khi quay lại màn hình, load lại danh sách
    }

}
