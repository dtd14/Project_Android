package com.example.project_android_ck.Khach_hang;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.project_android_ck.Data.DAO;
import com.example.project_android_ck.R;

import java.util.ArrayList;

public class chitiet_qlkh extends Activity {
    DAO dao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitiet_qlkh);
        dao = new DAO(chitiet_qlkh.this);
        ImageButton huy, luu;
        huy = findViewById(R.id.ibtn_huy);
        luu = findViewById(R.id.ibtn_luu);
        EditText ma, ten, sdt, email, diachi;
        ma = findViewById(R.id.edt_makh);
        ten = findViewById(R.id.edt_tenkh);
        sdt = findViewById(R.id.edt_sdt);
        email = findViewById(R.id.edt_email);
        diachi = findViewById(R.id.edt_diachi);
        ma.requestFocus();
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ab = new AlertDialog.Builder(chitiet_qlkh.this);
                ab.setTitle("Thông báo !");
                ab.setMessage("Trở lại ?");
                ab.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                ab.setNegativeButton("Không", null);
                ab.show();
            }
        });
        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ma.getText().toString().trim().isEmpty() ||
                        ten.getText().toString().trim().isEmpty() ||
                        sdt.getText().toString().trim().isEmpty() ||
                        diachi.getText().toString().trim().isEmpty() ||
                        email.getText().toString().trim().isEmpty()) {
                    AlertDialog.Builder success = new AlertDialog.Builder(chitiet_qlkh.this);
                    success.setTitle("Cảnh báo!");
                    success.setMessage("Hãy điền đầy đủ thông tin!");
                    success.setPositiveButton("OK", null);
                    success.show();
                } else {
                    AlertDialog.Builder ab = new AlertDialog.Builder(chitiet_qlkh.this);
                    ab.setTitle("Thông báo !");
                    ab.setMessage("Thêm khách hàng này ?");
                    ab.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                         long s = dao.add_khachhang(new Khach_hang(ma.getText().toString(), ten.getText().toString(), sdt.getText().toString(), email.getText().toString(), diachi.getText().toString()));
                                    if(s<=0)
        {
            Toast.makeText(chitiet_qlkh.this, "Thêm không thành công!", Toast.LENGTH_SHORT).show();
        }
                            AlertDialog.Builder success = new AlertDialog.Builder(chitiet_qlkh.this);
                            success.setTitle("Thành công!");
                            success.setMessage("Đã thêm khách hàng!");
                            success.setPositiveButton("OK", null);
                            success.show();
                            finish();
                        }
                    });
                    ab.setNegativeButton("Không", null);
                    ab.show();
                }
            }
        });
    }

}
