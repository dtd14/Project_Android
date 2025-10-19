package com.example.project_android_ck.NhaCungCap;





import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

;
import com.example.project_android_ck.R;

import java.util.ArrayList;

public class QuanLiNhaCungCap extends AppCompatActivity {

    private SearchView searchSupplier;
    private RecyclerView supplierList;
    private Button addSupplierButton;
    private ArrayList<NhaCungCap> nhaCungCapList;
    private NhaCungCapAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlydonhang_activity);

        searchSupplier = findViewById(R.id.search_supplier);
        supplierList = findViewById(R.id.supplier_list);
        addSupplierButton = findViewById(R.id.add_supplier_button);

        // Khởi tạo dữ liệu mẫu
        nhaCungCapList = new ArrayList<>();
        nhaCungCapList.add(new NhaCungCap("CT01","Công ty ABC", "123 Đường ABC", "0123456789", "abc@gmail.com", "Ghi chú ABC"));
        nhaCungCapList.add(new NhaCungCap("CT02","Công ty XYZ", "456 Đường XYZ", "0987654321", "xyz@gmail.com", "Ghi chú XYZ"));

        // Cấu hình RecyclerView
        LinearLayoutManager linear = new LinearLayoutManager(this);
        supplierList.setLayoutManager(linear);
        adapter = new NhaCungCapAdapter(this, nhaCungCapList, new NhaCungCapAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                nhaCungCapList.remove(position);
                adapter.notifyItemRemoved(position);
            }

            @Override
            public void onRestoreClick(int position) {
                // Logic khôi phục (có thể bỏ qua hoặc thêm logic tùy ý)
            }
        });
        Log.d("DEBUG", "Danh sach size = " + nhaCungCapList.size());
        supplierList.setAdapter(adapter);

        addSupplierButton.setOnClickListener(v -> {
            openAddSupplierForm();
        });
    }

    private void openAddSupplierForm() {
        Intent it = new Intent(this,ThemNhacc.class);
        startActivity(it);
    }
}
