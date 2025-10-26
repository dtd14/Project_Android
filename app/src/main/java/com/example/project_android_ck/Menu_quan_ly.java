package com.example.project_android_ck;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_android_ck.Donhang.quanlydonhang.Quanlydonhang.QuanLyDonHang;
import com.example.project_android_ck.Khach_hang.quanlykhachhang;
import com.example.project_android_ck.NhaCungCap.QuanLiNhaCungCap;
import com.example.project_android_ck.Quanlylaptop.MainLaptop;

public class Menu_quan_ly extends AppCompatActivity {
    ImageButton btnNhaCungCap, btnSanPham, btnHoaDon, btnKhachHang , btn_out;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_tong);

        btnNhaCungCap = findViewById(R.id.btnNhaCungCap);
        btnSanPham = findViewById(R.id.btnSanPham);
        btnHoaDon = findViewById(R.id.btnHoaDon);
        btnKhachHang = findViewById(R.id.btnKhachHanghh);
        btn_out = findViewById(R.id.btnout);

        btnNhaCungCap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu_quan_ly.this, QuanLiNhaCungCap.class));
            }
        });

        btnSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu_quan_ly.this, MainLaptop.class));
            }
        });

        btnHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu_quan_ly.this, QuanLyDonHang.class));
            }
        });

        btnKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu_quan_ly.this, quanlykhachhang.class));
            }
        });
        btn_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ab = new AlertDialog.Builder(Menu_quan_ly.this);
                ab.setTitle("Cảng Báo");
                ab.setMessage("Bạn có muốn thoát không?");
                ab.setPositiveButton("yes", new DialogInterface.OnClickListener() {
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
}
