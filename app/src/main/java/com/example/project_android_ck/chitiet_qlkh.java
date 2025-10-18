package com.example.project_android_ck;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

public class chitiet_qlkh extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitiet_qlkh);
        ImageButton xoa, huy, luu;
        xoa = findViewById(R.id.ibtn_xoa);
        huy = findViewById(R.id.ibtn_huy);
        luu = findViewById(R.id.ibtn_luu);
        EditText ma, ten, sdt, email, diachi;
        ma = findViewById(R.id.edt_makh);
        ten = findViewById(R.id.edt_tenkh);
        sdt = findViewById(R.id.edt_sdt);
        email = findViewById(R.id.edt_email);
        diachi = findViewById(R.id.edt_diachi);
        ma.requestFocus();
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ab = new AlertDialog.Builder(chitiet_qlkh.this);
                ab.setTitle("Thông báo !");
                ab.setMessage("Bạn có chắc muốn xóa hết dữ liệu đã nhập ?");
                ab.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ma.setText("");
                        ten.setText("");
                        sdt.setText("");
                        email.setText("");
                        diachi.setText("");
                        ma.requestFocus();
                    }
                });
                ab.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                ab.show();
            }
        });
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ab = new AlertDialog.Builder(chitiet_qlkh.this);
                ab.setTitle("Thông báo !");
                ab.setMessage("Trở lại ?");
                ab.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent it = new Intent(chitiet_qlkh.this, quanlykhachhang.class);
                        startActivity(it);
                    }
                });
                ab.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                ab.show();
            }
        });
        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ab = new AlertDialog.Builder(chitiet_qlkh.this);
                ab.setTitle("Thông báo !");
                ab.setMessage("Thêm khách hàng này ?");
                ab.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         //
                         //
                         //
                    }
                });
                ab.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                ab.show();
            }
        });
    }
}
