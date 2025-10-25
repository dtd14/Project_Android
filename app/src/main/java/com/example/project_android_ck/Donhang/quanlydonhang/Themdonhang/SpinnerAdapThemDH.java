package com.example.project_android_ck.Donhang.quanlydonhang.Themdonhang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project_android_ck.Khach_hang.Khach_hang;
import com.example.project_android_ck.Quanlylaptop.Laptop;
import com.example.project_android_ck.R;

import java.util.ArrayList;

public class SpinnerAdapThemDH extends BaseAdapter {
    private ArrayList<Laptop> danhsachlaptop;
    //Context trong Android có thể hiểu là “ngữ cảnh” hay “môi trường đang chạy của ứng dụng”.
    //Nó giúp bạn truy cập đến các tài nguyên (resource), layout, thông tin ứng dụng, và các dịch vụ hệ thống.
    //Ví dụ, nhờ context bạn có thể:
    //Lấy layout (LayoutInflater.from(context)),
    //Lấy chuỗi từ res/values/strings.xml,
    //Mở Activity khác bằng Intent(context, ...),
    //Hiển thị Toast.makeText(context, "Xin chào", Toast.LENGTH_SHORT).show().
    //Nếu không có context, các lệnh này không biết đang chạy trong màn hình nào, ứng dụng nào → sẽ bị lỗi.
    private Context context;
    private ArrayList<Khach_hang> dskh;

    public SpinnerAdapThemDH( Context context) {
        this.context = context;
        danhsachlaptop = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return danhsachlaptop.size();
    }

    @Override
    public Object getItem(int position) {
        return danhsachlaptop.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //tạo ra giao diện hiển thị của từng item trong Spinner.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //tạo 1 cái item(view) trong spinner
        View item = LayoutInflater.from(context).inflate(R.layout.item_spinner_sanpham,parent,false);

        //Tìm đến cái text view có id kia rồi gán giá trị vào
        TextView t = item.findViewById(R.id.tvspinsanpham);
        Laptop lt = danhsachlaptop.get(position);
        //đặt nội dung hiển thị cho TextView của từng item trong Spinner dựa trên dữ liệu ở vị trí position
        t.setText(lt.getTenLapTop());
        return item;
    }
}
