package com.example.project_android_ck.Quanlylaptop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project_android_ck.Data.DAO;
import com.example.project_android_ck.R;

import java.util.ArrayList;

public class MainLaptop extends Activity {
    private ListView lvlt;
    private ImageButton imgthemlt;
    private DAO dao;
    private LaptopAdapter adapter;
    private ArrayList<Laptop> listlaptop;
    private SearchView searchViewlaptop ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlylaptop_activity);

        dao = new DAO(this);
        SearchView searchView = findViewById(R.id.sv_qllt);
        lvlt = findViewById(R.id.lv_qllt);
        imgthemlt = findViewById(R.id.ibtn_themlt);
        searchViewlaptop = findViewById(R.id.sv_qllt);
        searchViewlaptop.setIconifiedByDefault(false);
        searchViewlaptop.setQueryHint("Tìm kiếm");
        searchViewlaptop.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }
        });

        // Lấy dữ liệu từ DB
        listlaptop = dao.select_Laptop();
        adapter = new LaptopAdapter(this, R.layout.item_quanlylaptop, listlaptop);
        lvlt.setAdapter(adapter);

        // Mở màn hình thêm laptop
        imgthemlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainLaptop.this, AddLaptop.class);
                startActivity(i);
            }
        });

        // Đăng ký context menu
        registerForContextMenu(lvlt);
    }

    private void performSearch(String keywork) {
        ArrayList<Laptop> result = new ArrayList<>();
        if(keywork.trim().isEmpty()){
            result = dao.select_Laptop();
        }else{
            result = dao.search_laptop(keywork);
        }
        listlaptop = result;
        adapter = new LaptopAdapter(this,R.layout.item_quanlylaptop,listlaptop);
        lvlt.setAdapter(adapter);
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Load lại dữ liệu khi quay về
        listlaptop.clear();
        listlaptop.addAll(dao.select_Laptop());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.laptop, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int position = info.position;
        final Laptop laptop = listlaptop.get(position);

        if(item.getItemId() == R.id.item_sualt)
        {
            // ĐỔI laptop1 → laptop
            Intent it = new Intent(MainLaptop.this, Update_Sanpham_Laptop.class);
            it.putExtra("laptop_mancc", laptop.getMaNCC());
            it.putExtra("laptop_malt", laptop.getMaLapTop());
            it.putExtra("laptop_ten", laptop.getTenLapTop());
            it.putExtra("laptop_sl", laptop.getSoluong());
            it.putExtra("laptop_dongia", laptop.getGia());
            startActivity(it);
            return true;
        }

        if (item.getItemId() == R.id.item_xoalt)
        {
            AlertDialog.Builder ab = new AlertDialog.Builder(MainLaptop.this);
            ab.setTitle("Xóa !");
            ab.setMessage("Xóa sản phẩm này ?");
            ab.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Xóa từ database
                    dao.delete_laptop(laptop);

                    // Xóa khỏi list và cập nhật adapter
                    listlaptop.remove(position);
                    adapter.notifyDataSetChanged();

                    // Hiển thị thông báo thành công
                    AlertDialog.Builder success = new AlertDialog.Builder(MainLaptop.this);
                    success.setTitle("Thành công!");
                    success.setMessage("Đã xóa sản phẩm !");
                    success.setPositiveButton("OK", null);
                    success.show();
                }
            });
            ab.setNegativeButton("Không", null);
            ab.show();
            return true;
        }

        return super.onContextItemSelected(item);
    }

}