package com.example.project_android_ck.Donhang.quanlydonhang.Quanlydonhang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project_android_ck.Quanlylaptop.Laptop;
import com.example.project_android_ck.R;

import java.util.ArrayList;

public class CustomAdapterQuanLyDonHang extends ArrayAdapter<DonHangFull> {
    private Context context;
    private ArrayList<DonHangFull> arrQuanLyDonHang;
    private ArrayList<DonHangFull> dsGoc;
    //tạo phương thức khởi tạo

    public CustomAdapterQuanLyDonHang(@NonNull Context context, int resource, ArrayList<DonHangFull> arrQuanLyDonHang) {
        super(context, resource,arrQuanLyDonHang);
        this.context = context;
        this.arrQuanLyDonHang = arrQuanLyDonHang;
        dsGoc = new ArrayList<>(arrQuanLyDonHang);
    }

    @NonNull
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            //xử lý dữ liệu, lọc danh sách
            protected FilterResults performFiltering(CharSequence constraint) {
                String keywword = constraint.toString();
                ArrayList<DonHangFull> dsKetQua = new ArrayList<>();
                if (keywword.isEmpty()) {
                    dsKetQua.addAll(dsGoc);
                } else {
                    for (DonHangFull dhf : dsGoc) {
                        if (dhf.getHoTen().trim().toLowerCase().contains(keywword.toLowerCase()) ||
                                dhf.getSoDienThoai().trim().toLowerCase().contains(keywword.toLowerCase())) {
                            dsKetQua.add(dhf);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = dsKetQua;
                return results;
            }

            //hiển thị lại danh sách sau khi lọc.
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                //xóa toàn bộ dữ liệu đi để đẩy dữ liệu mơí lên vào cập nhật lại listview
                arrQuanLyDonHang.clear();
                arrQuanLyDonHang.addAll((ArrayList<DonHangFull>) results.values);
                notifyDataSetChanged();
            }
        };
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView tvMaDH,tvTenKH,tvLapTop,tvNgaylap,tvTongtien;

        View itemView = LayoutInflater.from(context).inflate(R.layout.item_quanlydonhang,parent,false);
        tvMaDH = itemView.findViewById(R.id.tv_MaDH);
        tvTenKH = itemView.findViewById(R.id.tv_TenKH);
        tvLapTop = itemView.findViewById(R.id.tv_Laptop);
        tvNgaylap = itemView.findViewById(R.id.tv_Ngaylap);
        tvTongtien= itemView.findViewById(R.id.tv_Tongtien);

        // Đổ liệu từ ArrayList<DonHangFull> lên giao  diện
        DonHangFull dh = arrQuanLyDonHang.get(position);
        tvMaDH.setText(dh.getMaDH());
        tvTenKH.setText(dh.getHoTen());
        tvLapTop.setText(dh.getTenLapTop());
        tvNgaylap.setText(dh.getNgayLap());
        tvTongtien.setText(String.format("%.0f", dh.getTongTien()));
        return itemView;
    }
}
