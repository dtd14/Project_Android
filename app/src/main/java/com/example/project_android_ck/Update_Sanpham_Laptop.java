package com.example.project_android_ck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update_Sanpham_Laptop extends Activity {
    EditText edtTen, edtHang, edtGia, edtSoluong;
    Button btnUpdate;
    DatabaseHelper db;
    int laptopId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlylaptop3);

        edtTen = findViewById(R.id.edtName);
        edtHang = findViewById(R.id.edtBrand);
        edtGia = findViewById(R.id.edtPrice);
        edtSoluong = findViewById(R.id.edtQuantity);
        btnUpdate = findViewById(R.id.btnUpdate);

        db = new DatabaseHelper(this);
        // Lấy dữ liệu từ intent
        Intent intent = getIntent();
        laptopId = intent.getIntExtra("id", -1);
        edtTen.setText(intent.getStringExtra("ten"));
        edtHang.setText(intent.getStringExtra("hang"));
        edtGia.setText(String.valueOf(intent.getDoubleExtra("gia", 0)));
        edtSoluong.setText(String.valueOf(intent.getIntExtra("soluong", 0)));

        btnUpdate.setOnClickListener(v -> {
            String name = edtTen.getText().toString();
            String brand = edtHang.getText().toString();
            String priceStr = edtGia.getText().toString();
            String quantityStr = edtSoluong.getText().toString();
            if (name.isEmpty() || brand.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            double price;
            int quantity;
            try {
                price = Double.parseDouble(priceStr);
                quantity = Integer.parseInt(quantityStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Giá hoặc số lượng không hợp lệ", Toast.LENGTH_SHORT).show();
                return;
            }

            if (price <= 0 || quantity <= 0) {
                Toast.makeText(this, "Giá và số lượng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                return;
            }

            Laptop laptop = new Laptop(laptopId, name, brand, price, quantity);
            db.updateLaptop(laptop);

            Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
            finish(); // quay lại Main
        });
    }
}
