package com.example.project_android_ck.NhaCungCap;





import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

;
import com.example.project_android_ck.Data.DAO;
import com.example.project_android_ck.R;

import java.util.ArrayList;
import java.util.List;

public class QuanLiNhaCungCap extends AppCompatActivity {
    private DAO dao;

    private SearchView searchSupplier;
    private Spinner  spinner;
    private RecyclerView supplierList;
    private Button addSupplierButton,outButton;
    private ArrayList<NhaCungCap> nhaCungCapList;
    private NhaCungCapAdapter adapter;
    private String selectedField ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlinhacungcap_activity);

        searchSupplier = findViewById(R.id.search_supplier);
        spinner =  findViewById(R.id.spinner_filter);
        supplierList = findViewById(R.id.supplier_list);
        addSupplierButton = findViewById(R.id.add_supplier_button);
        outButton = findViewById(R.id.out);
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
                dao.backupBeforeUpdate(ncc);
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
                ab.setNegativeButton("Huy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ab.show();
            }

            @Override
            public void onDetailClick(NhaCungCap ncc) {
                Intent it = new Intent(QuanLiNhaCungCap.this, ChiTietNhaCungCap.class);
                it.putExtra("Supplier_id",ncc.getMancc());
                startActivity(it);
            }

            @Override
            public void onRestoreClick(NhaCungCap ncc) {
                AlertDialog.Builder ab = new AlertDialog.Builder(QuanLiNhaCungCap.this);
                ab.setTitle("Khoi Phuc Du Lieu");
                ab.setMessage("Ban co chac chan muon khoi phuc du lieu cua "+ncc.getMancc());
                ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean result = dao.restoreNhaCungCap(ncc.getMancc());
                        adapter.Update_NhaCungCap(dao.getallnhacc());
                        if(result){
                            Toast.makeText(QuanLiNhaCungCap.this, "Khoi Phuc du lieu thanh cong", Toast.LENGTH_SHORT).show();
                        }else
                            Toast.makeText(QuanLiNhaCungCap.this, "Dữ liệu đã giống với bản backup, không cần khôi phục", Toast.LENGTH_SHORT).show();
                    }
                });
                ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ab.show();

            }

        });
//        Log.d("DEBUG", "Danh sach size = " + nhaCungCapList.size());
        supplierList.setAdapter(adapter);

        addSupplierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddSupplierForm();
            }
        });
        // --- Spinner chọn trường tìm ---
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Tất cả","TenNCC","MaNCC", "DiaChi"}
        );
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedField = parent.getItemAtPosition(position).toString();
                performSearch(searchSupplier.getQuery().toString()); // realtime lọc lại

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        searchSupplier.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }
        });
        outButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ab = new AlertDialog.Builder(QuanLiNhaCungCap.this);
                ab.setTitle("Quay Lại");
                ab.setMessage("Bạn có muốn quay lại menu không?");
                ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ab.show();

            }
        });

    }



    private void performSearch(String keyword) {
        List<NhaCungCap> resultList = new ArrayList<>();

        if (keyword.trim().isEmpty()) {
            resultList = dao.getallnhacc(); // nếu rỗng thì lấy hết
        } else {
            resultList = dao.searchNhaCungCap(selectedField, keyword);
        }

        adapter.Update_NhaCungCap(resultList);
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
