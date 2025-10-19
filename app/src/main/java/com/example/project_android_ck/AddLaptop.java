package com.example.project_android_ck;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddLaptop extends Activity {
    EditText edtName, edtBrand, edtPrice, edtQuantity;
    Button btnSave;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlylaptop3);

        edtName = findViewById(R.id.edtName);
        edtBrand = findViewById(R.id.edtBrand);
        edtPrice = findViewById(R.id.edtPrice);
        edtQuantity = findViewById(R.id.edtQuantity);
        btnSave = findViewById(R.id.btnSave);

        db = new DatabaseHelper(this);

        btnSave.setOnClickListener(v -> {
            String name = edtName.getText().toString();
            String brand = edtBrand.getText().toString();
            String priceStr = edtPrice.getText().toString();
            String quantityStr = edtQuantity.getText().toString();
            if (name.isEmpty() || brand.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đ5ủ thông tin", Toast.LENGTH_SHORT).show();
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

            db.addLaptop(name, brand, price, quantity);
            Toast.makeText(this, "Đã thêm laptop", Toast.LENGTH_SHORT).show();
            finish(); // quay lại MainActivity
        });
    }
}
