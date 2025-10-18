package com.example.project_android_ck.quanlydonhang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project_android_ck.R;

import java.util.ArrayList;

//Mục đích của lớp CustomAdapterQuanLyDonHang
//
//Đây là adapter tùy chỉnh (Custom Adapter) dùng để:
//
//Nối danh sách dữ liệu (ArrayList<DonHang>) với giao diện RecyclerView.
//
//Mỗi item trong danh sách sẽ hiển thị thông tin một đơn hàng: mã, tên khách hàng, laptop, số lượng, giá, ngày lập, v.v.
public class CustomAdapterQuanLyDonHang extends ArrayAdapter<DonHangFull> {
    private Context context;
    private ArrayList<DonHangFull> arrQuanLyDonHang;

    //tạo phương thức khởi tạo

    public CustomAdapterQuanLyDonHang(@NonNull Context context, int resource, ArrayList<DonHangFull> arrQuanLyDonHang) {
        super(context, resource,arrQuanLyDonHang);
        this.context = context;
        this.arrQuanLyDonHang = arrQuanLyDonHang;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView tvMaDH,tvTenKH,tvLapTop,tvSoluong,tvGia,tvNgaylap,tvSDT,tvEmail,tvDiachi,tvMota,tvTongtien;
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_quanlydonhang,parent,false);
        tvMaDH = itemView.findViewById(R.id.tv_MaDH);
        tvTenKH = itemView.findViewById(R.id.tv_TenKH);
        tvLapTop = itemView.findViewById(R.id.tv_Laptop);
        tvSoluong = itemView.findViewById(R.id.tv_Soluong);
        tvGia = itemView.findViewById(R.id.tv_Gia);
        tvNgaylap = itemView.findViewById(R.id.tv_Ngaylap);
        tvSDT = itemView.findViewById(R.id.tv_SDT);
        tvEmail = itemView.findViewById(R.id.tv_Email);
        tvDiachi= itemView.findViewById(R.id.tv_Diachi);
        tvMota =itemView.findViewById(R.id.tv_Mota);
        tvTongtien= itemView.findViewById(R.id.tv_Tongtien);

        DonHangFull dh = arrQuanLyDonHang.get(position);
        tvMaDH.setText(dh.getMaDH());
        tvTenKH.setText(dh.getHoTen());
        tvLapTop.setText(dh.getSanPham());
        tvSoluong.setText(String.valueOf(dh.getSoLuong()));
        tvGia.setText(String.valueOf(dh.getGia()));
        tvNgaylap.setText(dh.getNgayLap());
        tvSDT.setText(dh.getSoDienThoai());
        tvEmail.setText(dh.getEmail());
        tvDiachi.setText(dh.getDiaChi());
        tvMota.setText(dh.getMoTa());
        tvTongtien.setText(String.valueOf(dh.getTongTien()));
        return itemView;
    }
}
