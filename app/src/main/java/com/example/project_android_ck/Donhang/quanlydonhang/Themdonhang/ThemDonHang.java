package com.example.project_android_ck.Donhang.quanlydonhang.Themdonhang;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.project_android_ck.Data.DAO;
import com.example.project_android_ck.Khach_hang.Khach_hang;
import com.example.project_android_ck.Quanlylaptop.Laptop;
import com.example.project_android_ck.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class ThemDonHang extends Activity {
    private AutoCompleteTextView auto_Ten;
    private Spinner Spin_SP;
    private EditText edt_Gia,edt_SoLuong,edt_SoDienThoai,edt_Email,edt_DiaChi,edt_TongTien,edt_MoTa,edt_NgayDat;
    private Button bt_Luu,bt_Huy;
//    private ArrayList<String> dsTenKH = new ArrayList<>();
    private ArrayList<Laptop> danhsachlaptop = new ArrayList<>();
    private ArrayList<Khach_hang> danhsachkhachhang = new ArrayList<>();
    private DAO dao;
    private String maKHChon="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themdonhang_activity);
        init();

        dao = new DAO(this);
        danhsachlaptop = dao.select_Laptop();
        danhsachkhachhang = dao.select_khachhang();

        // set spinner apdapter
        SpinnerAdapThemDH adapThemDH = new SpinnerAdapThemDH(this);
        Spin_SP.setAdapter(adapThemDH);
        Spin_SP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Lấy vị trí Laptop trong danh sách laptop để đưa giá tiền lên cùng
                Laptop lt = danhsachlaptop.get(position);
                edt_Gia.setText(String.valueOf(lt.getGia()));
                updateTongTien();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // lắng khi sự kiện khi viết vào Soluong
        edt_SoLuong.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            // mỗi khi thêm ký tự tong tien sẽ tự thay đổi
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateTongTien();
            }
        });


        //tạo adapter cho tên khách hàng
        ArrayAdapter<Khach_hang> adapKH = new ArrayAdapter<>(ThemDonHang.this, android.R.layout.simple_spinner_dropdown_item,danhsachkhachhang);
        auto_Ten.setAdapter(adapKH);
        auto_Ten.setThreshold(1);

        // lấy thông tin của khachs hàng trong danh sach khach hàng rồi gán vào edittext
        auto_Ten.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //So sánh tênnhapap vào với tên trong danh sách
                String tenchon = auto_Ten.getText().toString();
                for (Khach_hang kh : danhsachkhachhang){
                    if(kh.getTen().equalsIgnoreCase(tenchon)){
                        edt_DiaChi.setText(kh.getDiachi());
                        edt_Email.setText(kh.getEmail());
                        edt_SoDienThoai.setText(kh.getSdt());
                        maKHChon =kh.getMa();
                        break;
                    }
                }
            }
        });

        //Tạo ngày lập bằng DatePickerDialog
        edt_NgayDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int nam = c.get(Calendar.YEAR);
                int thang = c.get(Calendar.MONTH);
                int ngay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dpl = new DatePickerDialog(ThemDonHang.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        edt_NgayDat.setText(day+"/"+(month+1)+"/"+year);
                    }
                },nam,thang,ngay);
                dpl.show();
            }
        });


        //Hủy không thêm thông tin nữa
        bt_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Thêm khách hàng
        bt_Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoten,gia,ngaylap,sodienthoai,email,diachi,mota;
                //Lấy trên edittext để gán vào

                hoten = auto_Ten.getText().toString();
                gia = edt_Gia.getText().toString();
                if (edt_SoLuong.getText().toString().trim().isEmpty()) {
                    Toast.makeText(ThemDonHang.this, "Vui lòng nhập số lượng", Toast.LENGTH_SHORT).show();
                    return;
                }
                int sl = Integer.parseInt(edt_SoLuong.getText().toString());
                ngaylap =edt_NgayDat.getText().toString();
                sodienthoai = edt_SoDienThoai.getText().toString().trim();
                email= edt_Email.getText().toString().trim();
                diachi = edt_DiaChi.getText().toString().trim();
                Double tongtien = Double.parseDouble(edt_TongTien.getText().toString());
                mota = edt_MoTa.getText().toString().trim();
                Laptop lt = (Laptop) Spin_SP.getSelectedItem();
                String MaHD = taoNgauNhienMaDH();


                // them chitiet và them
               long check = dao.themDonHang(new DonHang(MaHD,maKHChon,ngaylap,tongtien,mota));
                dao.themChiTietDonHang(MaHD,String.valueOf(lt.getMaLapTop()),sl,lt.getGia());
                if (check >0){
                    Toast.makeText(ThemDonHang.this,"Thêm đơn hàng thành công",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(ThemDonHang.this,"Thêm thất bại",Toast.LENGTH_LONG).show();
                }
                finish();
            }

        });
    }
    public void updateTongTien(){
        String g = edt_Gia.getText().toString().trim();
        String sl = edt_SoLuong.getText().toString().trim();
        if (!g.isEmpty()&& !sl.isEmpty()){
            Double gia = Double.parseDouble(g);
            int soluong = Integer.parseInt(sl);
            edt_TongTien.setText(String.valueOf(gia*soluong));
        }
        else {
            edt_TongTien.setText("");
        }
    }
    public String taoNgauNhienMaDH(){
        Random rd = new Random();
        int random = 0 + rd.nextInt(100);
        return "DH"+random;
    }
    private void init(){
        auto_Ten= findViewById(R.id.auto_ten);
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
    @Override
    protected void onResume() {
        super.onResume();
        danhsachkhachhang.clear();
        danhsachkhachhang.addAll(dao.select_khachhang());
    }

}
