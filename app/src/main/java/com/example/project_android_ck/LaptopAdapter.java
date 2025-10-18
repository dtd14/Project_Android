package com.example.project_android_ck;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LaptopAdapter extends RecyclerView.Adapter<LaptopAdapter.ViewHolder> implements Filterable{
    private Context context;
    private List<Laptop> list;
    private List<Laptop> listfull;
    private DatabaseHelper db;
    public LaptopAdapter(Context context, List<Laptop> list, DatabaseHelper db) {
        this.context = context;
        this.list = (list != null) ? list : new ArrayList<>();
        this.listfull = new ArrayList<>(this.list);
        this.db = db;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quanlylaptop2, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Laptop laptop = list.get(position);
        holder.tvName.setText(laptop.name);
        holder.tvBrand.setText(laptop.brand);
        holder.tvPrice.setText(String.valueOf(laptop.price));
        holder.tvQuantity.setText(String.valueOf(laptop.quantity));

        holder.itemView.setOnLongClickListener(v -> {
            db.deleteLaptop(laptop.id);
            list.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Đã xóa " + laptop.name, Toast.LENGTH_SHORT).show();
            return true;
        });
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Update_Sanpham_Laptop.class);
            intent.putExtra("id", laptop.id);
            intent.putExtra("ten", laptop.name);
            intent.putExtra("hang", laptop.brand);
            intent.putExtra("gia", laptop.price);
            intent.putExtra("soluong", laptop.quantity);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvBrand, tvPrice, tvQuantity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvBrand = itemView.findViewById(R.id.tvBrand);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
        }
    }
    @Override
    public Filter getFilter() {
        return laptopFilter;
    }

    private Filter laptopFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Laptop> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(listfull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Laptop item : listfull) {
                    String name = item.getName() != null ? item.getName().toLowerCase() : "";
                    String brand = item.getBrand() != null ? item.getBrand().toLowerCase() : "";
                    if (name.contains(filterPattern) || brand.contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            if (results != null && results.values != null) {
                try {
                    list.addAll((List<Laptop>) results.values);
                } catch (ClassCastException e) {
                    e.printStackTrace(); // debug cho dễ
                }
            }
            notifyDataSetChanged();
        }
    };
}
