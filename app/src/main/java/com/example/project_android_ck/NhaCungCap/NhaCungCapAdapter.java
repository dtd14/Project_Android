package com.example.project_android_ck.NhaCungCap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.project_android_ck.R;

import java.util.ArrayList;
import java.util.List;

public class NhaCungCapAdapter extends RecyclerView.Adapter<NhaCungCapAdapter.ViewHolder> {

    private Context context;
    private ArrayList<NhaCungCap> nhaCungCapList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onDeleteClick(NhaCungCap ncc);
        void onUpdateClick(NhaCungCap ncc);
    }

    public NhaCungCapAdapter(Context context, ArrayList<NhaCungCap> nhaCungCapList ,OnItemClickListener listener) {
        this.context = context;
        this.nhaCungCapList = nhaCungCapList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listview, parent, false);
        Log.d("DEBUG", "onCreateViewHolder: Tạo ViewHolder mới");
        return new ViewHolder(view);
    }

    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.d("DEBUG", "onBindViewHolder: Gắn dữ liệu cho item ở vị trí " + position);
        NhaCungCap nhaCungCap = nhaCungCapList.get(position);
        holder.txtTen.setText(nhaCungCap.getTen());
        holder.txtdiachi.setText(nhaCungCap.getEmail());// Sử dụng email làm thông tin thứ hai
        holder.Sodt.setText(nhaCungCap.getSoDienThoai());
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = holder.getBindingAdapterPosition();
                Log.d("DEBUG","currentPosition:" + currentPosition);
                if (currentPosition != RecyclerView.NO_POSITION && listener != null) {
                    listener.onDeleteClick(nhaCungCapList.get(currentPosition));
                }
            }
        });

        holder.restoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = holder.getBindingAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION && listener != null) {
                    listener.onUpdateClick(nhaCungCapList.get(currentPosition));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return nhaCungCapList.size();
    }
    public void Update_NhaCungCap(List<NhaCungCap> Newlistncc){
        this.nhaCungCapList.clear();
        this.nhaCungCapList.addAll(Newlistncc);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTen;
        public TextView txtdiachi;
        public TextView Sodt;
        public ImageButton deleteButton;
        public ImageButton restoreButton;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txt_ten);
            txtdiachi = itemView.findViewById(R.id.txt_diachi);
            Sodt = itemView.findViewById(R.id.txt_sodt);

            deleteButton = itemView.findViewById(R.id.delete_button);
            restoreButton = itemView.findViewById(R.id.restore_button);
        }
    }
}
