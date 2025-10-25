package com.example.project_android_ck.Donhang.quanlydonhang.Chitietdonhang;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_android_ck.Data.DAO;
import com.example.project_android_ck.Donhang.quanlydonhang.Quanlydonhang.DonHangFull;
import com.example.project_android_ck.Donhang.quanlydonhang.Themdonhang.DonHang;
import com.example.project_android_ck.Donhang.quanlydonhang.Themdonhang.SpinnerAdapThemDH;
import com.example.project_android_ck.Quanlylaptop.Laptop;
import com.example.project_android_ck.R;

import java.util.ArrayList;
import java.util.Calendar;

public class ChiTietDonHang extends AppCompatActivity {
    private EditText edt_MaDon,edt_KhachHang,edt_SoLuong,edt_Gia,edt_NgayLap,edt_SoDT,edt_Email,edt_DiaChi,edt_TongTien,edt_MoTa;
    private Button bt_Sua,bt_Xoa;
    private Toolbar tb_ChiTietDonHang;
    private DAO dao;
    private Spinner spLapTop;
    private ArrayList<DonHangFull> DHFull;
    private SpinnerAdapThemDH SpadapDH;
    private String MaDH;
    private ArrayList<DonHang> donhang = new ArrayList<>();
    private ArrayList<Laptop> danhsachlaptop = new ArrayList<>();
    private int vitri = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitietdonhang_activity);
        init();
        dao = new DAO(this);
        DHFull =dao.selectThongTinDonHang();
        danhsachlaptop = dao.select_Laptop();

        SpadapDH = new SpinnerAdapThemDH(ChiTietDonHang.this,danhsachlaptop);
        spLapTop.setAdapter(SpadapDH);


        //Bắt dữ liệu truyền từ QLDH sang
        Intent intent = getIntent();
        MaDH = intent.getStringExtra("MaDH");


        // Đổ dữ liệu từ ArrayList lên giao diện thì phải lấy được vị trí
        //của  nó trong đơn hàng full
        for (DonHangFull dh:DHFull){
            if(dh.getMaDH().equals(MaDH)){
                edt_MaDon.setText(dh.getMaDH());
                edt_KhachHang.setText(dh.getHoTen());
                edt_SoLuong.setText(String.valueOf(dh.getSoLuong()));
                edt_NgayLap.setText(dh.getNgayLap());
                edt_SoDT.setText(dh.getSoDienThoai());
                edt_Email.setText(dh.getEmail());
                edt_DiaChi.setText(dh.getDiaChi());
                edt_TongTien.setText(String.valueOf(dh.getTongTien()));
                edt_MoTa.setText(dh.getMoTa());
                edt_Gia.setText(String.valueOf(dh.getGia()));
                String tenlaptop = dh.getTenLapTop();
                for (int i = 0;i <danhsachlaptop.size();i++){
                    if(danhsachlaptop.get(i).getTenLapTop().equals(tenlaptop)){
                        spLapTop.setSelection(i);
                        break;
                    }
                }
            }
        }


        // Lấy item Laptop có vị trí trong Danhsahch donhang full
        spLapTop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Laptop lt = danhsachlaptop.get(position);
                edt_Gia.setText(String.valueOf(lt.getGia()));
                updateTongTien();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //edit ngaylap
        edt_NgayLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int nam = c.get(Calendar.YEAR);
                int thang = c.get(Calendar.MONTH);
                int ngay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpl = new DatePickerDialog(ChiTietDonHang.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        edt_NgayLap.setText(day+"/"+(month+1)+"/"+year);
                    }
                },nam,thang,ngay);
                dpl.show();
            }
        });

        /// Sửa thông tin đơn hàng
        bt_Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String madh = edt_MaDon.getText().toString().trim();
                String tenkh = edt_KhachHang.getText().toString().trim();
                String ngaylap = edt_NgayLap.getText().toString().trim();
                String mota = edt_MoTa.getText().toString().trim();

                int sluong = Integer.parseInt(edt_SoLuong.getText().toString().trim());
                double gia = Double.parseDouble(edt_Gia.getText().toString().trim());
                double tongtien = sluong * gia;

                Laptop lt = (Laptop) spLapTop.getSelectedItem();
                String maLaptop = lt.getMaLapTop();



                // Lấy mã khách hàng theo tên
                String maKH = dao.layMaKhachHangTheoTen(tenkh);
                if (maKH == null) {
                    Toast.makeText(ChiTietDonHang.this, "Không tìm thấy khách hàng: " + tenkh, Toast.LENGTH_SHORT).show();
                    return;
                }

                // Cập nhật đơn hàng
                dao.updateDonHang(new DonHang(madh, maKH, ngaylap, tongtien, mota));
                // Cập nhật chi tiết đơn hàng
                dao.updateChiTietDonHang(new ChiTiet(madh, maLaptop, sluong, gia));
                finish();
            }
        });

        /// / Xóa đơn hàng
        bt_Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.xoaDonHang(MaDH);
                finish();
            }
        });

        // Khi mà text thay đổi
        edt_SoLuong.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateTongTien();
            }
        });
    }

    public void updateTongTien(){
        String gia = edt_Gia.getText().toString().trim();
        String soluong = edt_SoLuong.getText().toString().trim();
        if(!gia.isEmpty()&&!soluong.isEmpty()){
            edt_TongTien.setText(String.valueOf(Integer.parseInt(soluong)* Double.parseDouble(gia)));
        }
        else {
            edt_TongTien.setText("");
        }
    }
    private void init(){
        edt_MaDon = findViewById(R.id.edtMaDH);
        edt_KhachHang = findViewById(R.id.edtTenKH);
        spLapTop = findViewById(R.id.sp_SanPham);
        edt_SoLuong = findViewById(R.id.edtSoLuong);
        edt_Gia = findViewById(R.id.edtGia);
        edt_SoDT = findViewById(R.id.edtSDT);
        edt_Email = findViewById(R.id.edtEmail);
        edt_DiaChi = findViewById(R.id.edtDiaChi);
        edt_TongTien = findViewById(R.id.edtTongTien);
        edt_MoTa = findViewById(R.id.edtMoTa);
        edt_NgayLap = findViewById(R.id.edtNgayLap);
        bt_Sua = findViewById(R.id.btnSua);
        bt_Xoa = findViewById(R.id.btnXoa);
        tb_ChiTietDonHang = findViewById(R.id.tb_chitietdonhang);
    }
}
