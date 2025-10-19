package com.example.project_android_ck.NhaCungCap;





import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

;
import com.example.project_android_ck.Data.DAO;
import com.example.project_android_ck.R;

import java.util.ArrayList;

public class QuanLiNhaCungCap extends AppCompatActivity {
    private DAO dao;

    private SearchView searchSupplier;
    private RecyclerView supplierList;
    private Button addSupplierButton;
    private ArrayList<NhaCungCap> nhaCungCapList;
    private NhaCungCapAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlinhacungcap_activity);

        searchSupplier = findViewById(R.id.search_supplier);
        supplierList = findViewById(R.id.supplier_list);
        addSupplierButton = findViewById(R.id.add_supplier_button);
        dao = new DAO(this);

        // Khởi tạo dữ liệu mẫu
        nhaCungCapList = new ArrayList<>();
   //     nhaCungCapList.add(new NhaCungCap("CT01","Công ty ABC", "123 Đường ABC", "0123456789", "abc@gmail.com", "Ghi chú ABC"));
  //      nhaCungCapList.add(new NhaCungCap("CT02","Công ty XYZ", "456 Đường XYZ", "0987654321", "xyz@gmail.com", "Ghi chú XYZ"));

        // Cấu hình RecyclerView
        LinearLayoutManager linear = new LinearLayoutManager(this);
        supplierList.setLayoutManager(linear);
        adapter = new NhaCungCapAdapter(this, nhaCungCapList, new NhaCungCapAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(NhaCungCap ncc) {
//                nhaCungCapList.remove(position);
//                adapter.notifyItemRemoved(position);
                AlertDialog.Builder ab = new AlertDialog.Builder(QuanLiNhaCungCap.this);
                ab.setTitle("Xác nhận xóa");
                ab.setMessage("Bạn có chắc chắn muốn xóa nhà cung cấp này không?");
                ab.setPositiveButton("Okii", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int result = dao.delete_ncc(ncc); // gọi hàm xóa trong DAO
                        if(result > 0){
                            adapter.Update_NhaCungCap(dao.getallnhacc());
                            Toast.makeText(getApplicationContext(), "Xóa thành công!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Xóa thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                ab.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); // đóng dialog nếu không muốn xóa
                    }
                });

                ab.show();
            }

            @Override
            public void onUpdateClick(NhaCungCap ncc) {
                AlertDialog.Builder ab = new AlertDialog.Builder(QuanLiNhaCungCap.this);
                View view = getLayoutInflater().inflate(R.layout.item_update_ncc,null) ;

                EditText edt_ma = view.findViewById(R.id.edit_ma_nha_cung_cap_ud);
                EditText edt_ten= view.findViewById(R.id.edit_ten_nha_cung_cap_ud);
                EditText edt_diachi = view.findViewById(R.id.edit_dia_chi_ud);
                EditText edt_sodt = view.findViewById(R.id.edit_so_dien_thoai_ud);
                EditText edt_email = view.findViewById(R.id.edit_email_ud);
                EditText edt_ghichu = view.findViewById(R.id.edit_ghichu_ud);
                edt_ma.setText(ncc.getMancc());
                edt_ma.setEnabled(false);
                edt_ten.setText(ncc.getTen());
                edt_diachi.setText(ncc.getDiaChi());
                edt_sodt.setText(ncc.getSoDienThoai());
                edt_email.setText(ncc.getEmail());
                edt_ghichu.setText(ncc.getGhiChu());
                ab.setView(view);
        //        ab.setTitle("Sua thong tin"+ncc.getTen());
                ab.setPositiveButton("Sua", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tenMoi = edt_ten.getText().toString().trim();
                        String diaChiMoi = edt_diachi.getText().toString().trim();
                        String soDtMoi = edt_sodt.getText().toString().trim();
                        String emailMoi = edt_email.getText().toString().trim();
                        String ghiChuMoi = edt_ghichu.getText().toString().trim();

                        // Kiểm tra trống
                        if (tenMoi.isEmpty() || diaChiMoi.isEmpty() || soDtMoi.isEmpty() || emailMoi.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // Cập nhật thông tin vào đối tượng
                        ncc.setTen(tenMoi);
                        ncc.setDiaChi(diaChiMoi);
                        ncc.setSoDienThoai(soDtMoi);
                        ncc.setEmail(emailMoi);
                        ncc.setGhiChu(ghiChuMoi);

                        // Gọi DAO update
                        int result = dao.Update_ncc(ncc);

                        if (result > 0) {
                            adapter.Update_NhaCungCap(dao.getallnhacc());
                            Toast.makeText(getApplicationContext(), "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Cập nhật thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    }


                });
                ab.show();



            }
        });
//        Log.d("DEBUG", "Danh sach size = " + nhaCungCapList.size());
        supplierList.setAdapter(adapter);

        addSupplierButton.setOnClickListener(v -> {
            openAddSupplierForm();
        });
    }

    private void openAddSupplierForm() {
        Intent it = new Intent(this,ThemNhacc.class);
        startActivity(it);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.Update_NhaCungCap(new DAO(this).getallnhacc());
    }
}
