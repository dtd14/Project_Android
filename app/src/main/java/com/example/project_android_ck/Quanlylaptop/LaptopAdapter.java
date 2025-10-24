package com.example.project_android_ck.Quanlylaptop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_android_ck.Data.DAO;
import com.example.project_android_ck.R;

import java.util.ArrayList;
import java.util.List;

public class LaptopAdapter extends ArrayAdapter<Laptop> {
    private Context context;
    private ArrayList<Laptop> listfull;
    private int resource;
    private DAO dao;
    public LaptopAdapter(@NonNull Context context, int resource, ArrayList<Laptop> listfull) {
        super(context, resource,listfull);
        this.context = context;
        this.listfull =listfull;
        this.resource =resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView tv_malaptop,tv_tenlaptop,tv_gia,tv_soluong;
        View item = LayoutInflater.from(context).inflate(R.layout.item_quanlylaptop,parent,false);

        tv_malaptop = item.findViewById(R.id.tv_maLapTop);
        tv_tenlaptop = item.findViewById(R.id.tv_tenLaptop);
        tv_gia = item.findViewById(R.id.tv_Gia);
        tv_soluong = item.findViewById(R.id.tv_soLuong);

        Laptop lt = listfull.get(position);
        tv_malaptop.setText(lt.getMaLapTop());
        tv_tenlaptop.setText(lt.getTenLapTop());
        tv_gia.setText(String.valueOf(lt.getGia()));
        tv_soluong.setText(String.valueOf(lt.getSoluong()));

        return item;
    }
}
