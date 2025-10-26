package com.example.project_android_ck.Khach_hang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project_android_ck.R;

import java.util.ArrayList;

public class Custom_adapter_qlkh extends ArrayAdapter<Khach_hang> implements Filterable {
    int resource;
    Context context;
    ArrayList<Khach_hang> arrayList;
    ArrayList<Khach_hang> arrayList_old;

    public Custom_adapter_qlkh(@NonNull Context context, int resource, ArrayList<Khach_hang> arrayList) {
        super(context, resource, arrayList);
        this.resource = resource;
        this.context = context;
        this.arrayList = arrayList;
        this.arrayList_old = new ArrayList<>(arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_khachhang,parent,false);
        TextView ma, ten, sdt, email, diachi;
        ma = view.findViewById(R.id.tv_makh);
        ten = view.findViewById(R.id.tv_tenkh);
        sdt = view.findViewById(R.id.tv_sdt);
        email = view.findViewById(R.id.tv_email);
        diachi = view.findViewById(R.id.tv_diachi);

        Khach_hang kh = arrayList.get(position);
        ma.setText(kh.getMa());
        ten.setText(kh.getTen());
        sdt.setText(kh.getSdt());
        email.setText(kh.getEmail());
        diachi.setText(kh.getDiachi());
        return view;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search = constraint.toString().toLowerCase().trim();
                FilterResults results = new FilterResults();
                if (search.isEmpty())
                {
                    results.values = arrayList_old;
                }
                else {
                    ArrayList<Khach_hang> filtered = new ArrayList<>();
                    for(Khach_hang kh : arrayList_old)
                    {
                        if (kh.getMa().toLowerCase().contains(search)||kh.getTen().toLowerCase().contains(search)||
                        kh.getSdt().toLowerCase().contains(search)||kh.getEmail().toLowerCase().contains(search)||
                        kh.getDiachi().toLowerCase().contains(search))
                        {
                            filtered.add(kh);
                        }
                    }
                    results.values = filtered;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                arrayList.clear();
                arrayList.addAll((ArrayList<Khach_hang>) results.values);
                notifyDataSetChanged();
            }
        };
    }
}
