package com.example.project_android_ck.NhaCungCap;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.project_android_ck.Data.DAO;
import com.example.project_android_ck.R;

public class ChiTietNhaCungCap extends AppCompatActivity {
    DAO dao;
    Toolbar toolbar;
    EditText editMaNCC, editTenNCC, editDiaChi, editSDT, editEmail,
            editMaSP, editLoaiSP, editTenSP, editGhiChu;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietnhacungcap_activity);
        toolbar = findViewById(R.id.toolbar_detail_supplier);
        dao = new DAO(this);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("Chi Tiet Nha Cung Cap");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        editMaNCC = findViewById(R.id.edit_ma_nha_cung_cap_ct);
        editTenNCC = findViewById(R.id.edit_ten_nha_cung_cap_ct);
        editDiaChi = findViewById(R.id.edit_dia_chi_ct);
        editSDT = findViewById(R.id.edit_so_dien_thoai_ct);
        editEmail = findViewById(R.id.edit_email_ct);
        editMaSP = findViewById(R.id.edit_loai_san_pham_ct);
        editLoaiSP = findViewById(R.id.edit_ma_san_pham_ct);
        editTenSP = findViewById(R.id.edit_ten_san_pham_ct);
        editGhiChu = findViewById(R.id.edit_ghichu_ct);
        // Nhận mã NCC
        Intent it = getIntent();
        String maNCC = it.getStringExtra("Supplier_id");
        if (maNCC != null) {
            hienThiChiTiet(maNCC);
        }

    }

    private void hienThiChiTiet(String maNCC) {
        Cursor cursor = dao.getChiTietNhaCungCap(maNCC);
        if (cursor != null && cursor.moveToFirst()) {
            editMaNCC.setText(cursor.getString(cursor.getColumnIndexOrThrow("MaNCC")));
            editTenNCC.setText(cursor.getString(cursor.getColumnIndexOrThrow("TenNCC")));
            editDiaChi.setText(cursor.getString(cursor.getColumnIndexOrThrow("DiaChi")));
            editSDT.setText(cursor.getString(cursor.getColumnIndexOrThrow("DienThoai")));
            editEmail.setText(cursor.getString(cursor.getColumnIndexOrThrow("Email")));

            // Laptop có thể null nên cần check
            String maLaptop = cursor.getString(cursor.getColumnIndexOrThrow("MaLaptop"));
            String tenLaptop = cursor.getString(cursor.getColumnIndexOrThrow("TenLaptop"));
//            String loaiLaptop = cursor.getString(cursor.getColumnIndexOrThrow("LoaiLaptop"));

            editMaSP.setText(maLaptop != null ? maLaptop : "Chưa có");
//            editLoaiSP.setText(loaiLaptop != null ? loaiLaptop : "Chưa có");
            editTenSP.setText(tenLaptop != null ? tenLaptop : "Chưa có");
            editGhiChu.setText(cursor.getString(cursor.getColumnIndexOrThrow("GhiChu")));
        }

        // Không cho chỉnh sửa
        editMaNCC.setEnabled(false);
        editTenNCC.setEnabled(false);
        editDiaChi.setEnabled(false);
        editSDT.setEnabled(false);
        editEmail.setEnabled(false);
        editMaSP.setEnabled(false);
        editLoaiSP.setEnabled(false);
        editTenSP.setEnabled(false);
        editGhiChu.setEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            getOnBackPressedDispatcher().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
