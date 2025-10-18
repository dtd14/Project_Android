package com.example.project_android_ck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Custom_adapter_qlkh extends ArrayAdapter<Khach_hang> {
    int resource;
    Context context;
    ArrayList<Khach_hang> arrayList;

    public Custom_adapter_qlkh(@NonNull Context context, int resource, ArrayList<Khach_hang> arrayList) {
        super(context, resource, arrayList);
        this.resource = resource;
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_khachhang,parent,false);
        TextView ma, ten, sdt, email, diachi;
        ma = view.findViewById(R.id.tv_makh);
        ten = view.findViewById(R.id.tv_tenkh);
        sdt = view.findViewById(R.id.tv_sdt);
        diachi = view.findViewById(R.id.tv_diachi);

        Khach_hang kh = arrayList.get(position);
        ma.setText(kh.getMa());
        ten.setText(kh.getTen());
        sdt.setText(kh.getSdt());
        diachi.setText(kh.getDiachi());
        return view;
    }

}
