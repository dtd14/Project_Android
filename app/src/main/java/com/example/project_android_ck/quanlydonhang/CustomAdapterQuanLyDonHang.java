package com.example.project_android_ck.quanlydonhang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_android_ck.R;

import java.util.ArrayList;

public class CustomAdapterQuanLyDonHang extends RecyclerView.Adapter<CustomAdapterQuanLyDonHang.DonHangViewHolder> {
    private Context context;
    private ArrayList<DonHang> arrQuanLyDonHang;

    //tạo phương thức khởi tạo
    public CustomAdapterQuanLyDonHang(Context context, ArrayList<DonHang> arrQuanLyDonHang) {
        this.context = context;
        this.arrQuanLyDonHang = arrQuanLyDonHang;
    }


    /*
        Khi RecyclerView cần tạo một item mới → hàm này sẽ được gọi.

        LayoutInflater.from(context).inflate(...) biến file item_quanlydonhang.xml thành một View.

        Sau đó trả về một DonHangViewHolder để quản lý view đó.
    */
    @NonNull
    @Override
    public DonHangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_quanlydonhang,parent,false);
        return new DonHangViewHolder(view);
    }

    //gắn kết icon với dữ liệu
    @Override
    public void onBindViewHolder(@NonNull DonHangViewHolder holder, int position) {
        DonHang donHang = arrQuanLyDonHang.get(position);

        holder.tvMaDH.setText(donHang.getMaDH());
        holder.tvTenKH.setText(donHang.getHoTen());
        holder.tvLapTop.setText(donHang.getSanPham());
        holder.tvGia.setText(String.valueOf(donHang.getGia()));
        holder.tvSoluong.setText(String.valueOf(donHang.getSoLuong()));
        holder.tvSDT.setText(donHang.getSoDienThoai());
        holder.tvEmail.setText(donHang.getEmail());
        holder.tvDiachi.setText(donHang.getDiaChi());
        holder.tvTongtien.setText(String.valueOf(donHang.getTongTien()));
        holder.tvMota.setText(donHang.getMoTa());
        holder.tvNgaylap.setText(donHang.getNgayLap());
    }
    //trả về trong cái RecyclerView có bao nhiêu Item
    @Override
    public int getItemCount() {
        return arrQuanLyDonHang.size();
    }


    //Định nghĩa ViewHolder
    /*
        DonHangViewHolder là lớp giữ tham chiếu tới các TextView trong 1 item.

        Mục đích: tránh gọi findViewById() nhiều lần (gây chậm).

        Khi tạo ViewHolder, nó sẽ ánh xạ các TextView trong item_quanlydonhang.xml vào biến Java.
    */
    public class DonHangViewHolder extends RecyclerView.ViewHolder{
        private TextView tvMaDH,tvTenKH,tvLapTop,tvSoluong,tvGia,tvNgaylap,tvSDT,tvEmail,tvDiachi,tvMota,tvTongtien;
        public DonHangViewHolder(@NonNull View itemView) {
            super(itemView);
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
        }
    }
}
