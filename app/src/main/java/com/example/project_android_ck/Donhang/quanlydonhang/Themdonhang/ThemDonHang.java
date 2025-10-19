package com.example.project_android_ck.Donhang.quanlydonhang.Themdonhang;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.example.project_android_ck.R;

import java.util.ArrayList;

public class ThemDonHang extends Activity {
    private AutoCompleteTextView auto_Ten;
    private Spinner Spin_SP;
    private EditText edt_Gia,edt_SoLuong,edt_SoDienThoai,edt_Email,edt_DiaChi,edt_TongTien,edt_MoTa,edt_NgayDat;
    private Button bt_Luu,bt_Huy;
    private ArrayList<String> danhsachlaptop = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themdonhang_activity);
        init();
        SpinnerAdapThemDH adapThemDH = new SpinnerAdapThemDH(this);
        Spin_SP.setAdapter(adapThemDH);
        //Hủy không thêm thông tin nữa
        bt_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        // Thêm khách hàng
        bt_Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setThongTin(){

    }
    private void init(){
        auto_Ten= findViewById(R.id.auto_ten);
        Spin_SP = findViewById(R.id.sp_SanPham);
        edt_DiaChi = findViewById(R.id.edt_diachi);
        edt_Gia = findViewById(R.id.edt_gia);
        edt_SoLuong = findViewById(R.id.edt_soluong);
        edt_SoDienThoai = findViewById(R.id.edt_sodienthoai);
        edt_Email = findViewById(R.id.edt_email);
        edt_TongTien = findViewById(R.id.edt_tongtien);
        edt_MoTa = findViewById(R.id.edt_mota);
        edt_NgayDat=findViewById(R.id.edt_ngaydat);
        bt_Luu = findViewById(R.id.bt_luu);
        bt_Huy = findViewById(R.id.bt_huy);
        Spin_SP = findViewById(R.id.sp_SanPham);
    }
}
