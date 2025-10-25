package com.example.project_android_ck.Quanlylaptop;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_android_ck.Data.DAO;
import com.example.project_android_ck.R;

import java.util.ArrayList;

public class Update_Sanpham_Laptop extends AppCompatActivity {

    private EditText edtMaLaptop, edtTenLaptop, edtSoLuong, edtDonGia;
    private Spinner spMaNCC;
    private Button btSua;
    private ArrayList<String> dsMaNCC;
    private DAO dao;
    private String maLaptop; // Mã laptop được truyền từ màn hình danh sách

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sualaptop_activity);

        edtMaLaptop = findViewById(R.id.edit_malaptop);
        edtTenLaptop = findViewById(R.id.edit_tenlaptop);
        edtSoLuong = findViewById(R.id.edit_soluong);
        edtDonGia = findViewById(R.id.edit_dongia);
        spMaNCC = findViewById(R.id.spn_ncc);
        btSua = findViewById(R.id.bt_capnhat);

        dao = new DAO(this);

        // Lấy danh sách mã NCC
        dsMaNCC = dao.getDanhSachMaNCC();
        ArrayAdapter<String> adapMaNCC = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dsMaNCC);
        spMaNCC.setAdapter(adapMaNCC);

        // Lấy mã laptop được truyền qua từ màn hình danh sách
        maLaptop = getIntent().getStringExtra("laptop_malt");

        // Hiển thị dữ liệu cũ lên giao diện
        if (maLaptop != null) {
            Laptop lt = dao.getLaptopById(maLaptop);
            if (lt != null) {
                edtMaLaptop.setText(lt.getMaLapTop());
                edtTenLaptop.setText(lt.getTenLapTop());
                edtSoLuong.setText(String.valueOf(lt.getSoluong()));
                edtDonGia.setText(String.valueOf(lt.getGia()));

                // chọn đúng mã NCC trong spinner
                for (int i = 0; i < dsMaNCC.size(); i++) {
                    if (dsMaNCC.get(i).equals(lt.getMaNCC())) {
                        spMaNCC.setSelection(i);
                        break;
                    }
                }

                // Không cho sửa mã laptop
                edtMaLaptop.setEnabled(false);
            }
        }

        // Xử lý nút Sửa
        btSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenLT = edtTenLaptop.getText().toString().trim();
                int soLuong = Integer.parseInt(edtSoLuong.getText().toString().trim());
                double donGia = Double.parseDouble(edtDonGia.getText().toString().trim());
                String maNCC = spMaNCC.getSelectedItem().toString();

                Laptop ltMoi = new Laptop(maLaptop, tenLT, soLuong, donGia, maNCC);

                int check = dao.updateLapTop(ltMoi);
                if (check > 0) {
                    Toast.makeText(Update_Sanpham_Laptop.this, "Cập nhật thành công!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(Update_Sanpham_Laptop.this, "Cập nhật thất bại!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
