package com.example.project_android_ck.Quanlylaptop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_android_ck.R;

import java.util.ArrayList;
import java.util.List;

public class LaptopAdapter extends ArrayAdapter<Laptop> {
    private Context context;
    private ArrayList<Laptop> listfull;
    private int resource;
    public LaptopAdapter(@NonNull Context context, int resource, ArrayList<Laptop> listfull) {
        super(context, resource,listfull);
        this.context = context;
        this.listfull =listfull;
        this.resource =resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position,convertView,parent);
    }
}
