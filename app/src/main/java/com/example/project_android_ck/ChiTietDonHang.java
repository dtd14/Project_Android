package com.example.project_android_ck;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ChiTietDonHang extends AppCompatActivity {
    private TextView tv_MaDon,tv_KhachHang,tv_TongTien,tv_NgayDat,tv_DiaChi,tv_MoTa;
    private RecyclerView rc_ChiTietDonHang;
    private Button bt_Sua,bt_Xoa;
    private Toolbar tb_ChiTietDonHang;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietdonhang_activity);
        init();
    }

    private void init(){
        tv_MaDon = findViewById(R.id.tv_madon);
        tv_KhachHang = findViewById(R.id.tv_khachhang);
        tv_TongTien = findViewById(R.id.tv_tongtien);
        tv_NgayDat = findViewById(R.id.tv_ngaydat);
        tv_DiaChi = findViewById(R.id.tv_diachi);
        tv_MoTa = findViewById(R.id.tv_mota);
        rc_ChiTietDonHang = findViewById(R.id.rc_donhang_chitiet);
        bt_Sua = findViewById(R.id.bt_update);
        bt_Xoa = findViewById(R.id.bt_xoa);
        tb_ChiTietDonHang = findViewById(R.id.tb_chitietdonhang);
    }
}
