package com.example.project_android_ck.NhaCungCap;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_android_ck.R;

public class ThemNhacc extends AppCompatActivity {
    EditText edt_ma, edt_ten , edt_diachi , edt_sodt , edt_email , edt_ghichu;
    Button btn_lua , btn_huy;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themncc);
    }
}
