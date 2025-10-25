package com.example.project_android_ck.Quanlylaptop;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_android_ck.Data.DAO;
import com.example.project_android_ck.R;

import java.util.ArrayList;

public class AddLaptop extends AppCompatActivity {
    private EditText edtmalaptop, edttenlaptop, edtsoluong, edtdongia;
    private Spinner sp_mancc;
    private Button bt_themlt;
    private ArrayList<String> dsMaNCC;
    private DAO dao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themlaptop_activity);

        edtmalaptop = findViewById(R.id.edit_malaptop);
        edttenlaptop = findViewById(R.id.edit_tenlaptop);
        edtsoluong = findViewById(R.id.edit_soluong);
        edtdongia = findViewById(R.id.edit_dongia);
        sp_mancc = findViewById(R.id.spn_ncc);
        bt_themlt = findViewById(R.id.bt_themlaptop);

        dao = new DAO(this);
        dsMaNCC = dao.getDanhSachMaNCC();

        ArrayAdapter<String> adapma = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dsMaNCC);
        sp_mancc.setAdapter(adapma);

        bt_themlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String maLT = edtmalaptop.getText().toString().trim();
                    String tenLT = edttenlaptop.getText().toString().trim();
                    int soLuong = Integer.parseInt(edtsoluong.getText().toString().trim());
                    double donGia = Double.parseDouble(edtdongia.getText().toString().trim());
                    String maNCC = sp_mancc.getSelectedItem().toString();

                    if (maLT.isEmpty() || tenLT.isEmpty()) {
                        Toast.makeText(AddLaptop.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    long check = dao.themLapTop(new Laptop(maLT, tenLT, soLuong, donGia, maNCC));

                    if (check > 0) {
                        Toast.makeText(AddLaptop.this, "Thêm laptop thành công", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(AddLaptop.this, "Thêm thất bại", Toast.LENGTH_LONG).show();
                    }

                    finish(); // Quay lại MainLaptop -> onResume() sẽ tự load lại dữ liệu
                } catch (Exception e) {
                    Toast.makeText(AddLaptop.this, "Lỗi nhập liệu!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
