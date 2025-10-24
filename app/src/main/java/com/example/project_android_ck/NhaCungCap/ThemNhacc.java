package com.example.project_android_ck.NhaCungCap;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.project_android_ck.Data.DAO;
import com.example.project_android_ck.R;

public class ThemNhacc extends AppCompatActivity {
    DAO dao;
    Toolbar toolbar;
    NhaCungCapAdapter ncc_adapter;

    EditText edt_ma, edt_ten , edt_diachi , edt_sodt , edt_email , edt_ghichu;
    Button btn_lua , btn_huy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themncc);
        edt_ma = findViewById(R.id.edit_ma_nha_cung_cap);
        edt_ten= findViewById(R.id.edit_ten_nha_cung_cap);
        edt_diachi = findViewById(R.id.edit_dia_chi);
        edt_sodt = findViewById(R.id.edit_so_dien_thoai);
        edt_email = findViewById(R.id.edit_email);
        edt_ghichu = findViewById(R.id.edit_ghichu);
        toolbar = findViewById(R.id.toolbarsupplier);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Them Nha Cung Cap");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        dao=new DAO(this);
        btn_lua = findViewById(R.id.button_luu);
        btn_huy = findViewById(R.id.button_huy);
        btn_lua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save_supperlier();
            }
        });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cancel_supperlier();
            }
        });

    }
    private void Save_supperlier() {
        String Mancc= edt_ma.getText().toString().trim();
        String Tenncc = edt_ten.getText().toString().trim();
        String Diachincc = edt_diachi.getText().toString().trim();
        String Emailcc = edt_email.getText().toString().trim();
        String Sodtcnn = edt_sodt.getText().toString().trim();
        String Ghichuncc= edt_ghichu.getText().toString().trim();
        // Kiểm tra trống
        if(Mancc.isEmpty() || Tenncc.isEmpty() || Diachincc.isEmpty() || Emailcc.isEmpty() || Sodtcnn.isEmpty()) {
            Toast.makeText(ThemNhacc.this, "Không được để trống các trường dữ liệu", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo AlertDialog xác nhận
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("Xác nhận");
        ab.setMessage("Bạn có chắc chắn muốn thêm nhà cung cấp này không?");
        ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Thêm dữ liệu
                long result = dao.add_nhacc(new NhaCungCap(Mancc,Tenncc,Diachincc,Sodtcnn,Emailcc,Ghichuncc));
                if(result>0){
                    Toast.makeText(ThemNhacc.this, "Thêm thành công nhà cung cấp!", Toast.LENGTH_SHORT).show();
                    // Xóa dữ liệu sau khi lưu
                    edt_ma.setText("");
                    edt_ten.setText("");
                    edt_diachi.setText("");
                    edt_email.setText("");
                    edt_sodt.setText("");
                    edt_ghichu.setText("");
                    edt_ma.requestFocus();
                    finish(); // đóng activity
                }else{
                    Toast.makeText(ThemNhacc.this, "Lỗi: Mã nhà cung cấp đã tồn tại!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // không làm gì, chỉ đóng dialog
            }
        });
        ab.show();


    }

    private void Cancel_supperlier() {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("Ban co chac chan muon huy?");
        ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                edt_ma.setText("");
                edt_ten.setText("");
                edt_diachi.setText("");
                edt_email.setText("");
                edt_sodt.setText("");
                edt_ghichu.setText("");
                edt_ma.requestFocus();
                Toast.makeText(getApplicationContext(), "Đã hủy nhập thông tin!", Toast.LENGTH_SHORT).show();
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getOnBackPressedDispatcher().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

