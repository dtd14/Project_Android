package com.example.project_android_ck.Khach_hang;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_android_ck.Data.DAO;
import com.example.project_android_ck.QuanLyDonHang;
import com.example.project_android_ck.R;

import java.util.ArrayList;

public class quanlykhachhang extends Activity {
    ListView lv_qlkh;
    DAO dao;
    public ArrayList<Khach_hang> arr;
    public Custom_adapter_qlkh adap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlykhachhang);
        dao = new DAO(quanlykhachhang.this);
        ImageButton them;
        SearchView sv = findViewById(R.id.sv_qlkh);
        lv_qlkh = findViewById(R.id.lv_qlkh);
        arr = new ArrayList<>();

        getlistview_khachhang();
        registerForContextMenu(lv_qlkh);
        them = findViewById(R.id.ibtn_themkh);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                adap.getFilter().filter(newText);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent (quanlykhachhang.this, chitiet_qlkh.class);
                startActivity(it);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        getlistview_khachhang();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position; //Láy vị trí item trong ListView
        Khach_hang kh = arr.get(position);

        if(item.getItemId()==R.id.item_sua)
        {
            Dialog dl = new Dialog(quanlykhachhang.this);
            dl.setContentView(R.layout.chitiet_khachhang);
            Khach_hang khach_hang = arr.get(position);
            EditText ma,ten,sdt,email,diachi;
            ImageButton luu,huy;
            ma = dl.findViewById(R.id.edt_makh);
            ten = dl.findViewById(R.id.edt_tenkh);
            sdt = dl.findViewById(R.id.edt_sdt);
            email = dl.findViewById(R.id.edt_email);
            diachi = dl.findViewById(R.id.edt_diachi);
            luu = dl.findViewById(R.id.ibtn_luu);
            huy = dl.findViewById(R.id.ibtn_huy);

            ma.setText(kh.getMa());
            ma.setEnabled(false);
            ten.setText(kh.getTen());
            sdt.setText(kh.getSdt());
            email.setText(kh.getEmail());
            diachi.setText(kh.getDiachi());
            huy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder ab = new AlertDialog.Builder(quanlykhachhang.this);
                    ab.setTitle("Thông báo !");
                    ab.setMessage("Trở lại ?");
                    ab.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dl.dismiss();
                        }
                    });
                    ab.setNegativeButton("Không", null);
                    ab.show();
                }
            });
            luu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ma.getText().toString().trim().isEmpty() ||
                            ten.getText().toString().trim().isEmpty() ||
                            sdt.getText().toString().trim().isEmpty() ||
                            diachi.getText().toString().trim().isEmpty() ||
                            email.getText().toString().trim().isEmpty()) {
                        AlertDialog.Builder success = new AlertDialog.Builder(quanlykhachhang.this);
                        success.setTitle("Cảnh báo!");
                        success.setMessage("Hãy điền đầy đủ thông tin!");
                        success.setPositiveButton("OK", null);
                        success.show();
                    } else {
                        AlertDialog.Builder ab = new AlertDialog.Builder(quanlykhachhang.this);
                        ab.setTitle("Thông báo !");
                        ab.setMessage("Cập nhật khách hàng này ?");
                        ab.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dao.update_khachhang(new Khach_hang(ma.getText().toString(), ten.getText().toString(), sdt.getText().toString(), email.getText().toString(), diachi.getText().toString()));
                                dl.dismiss();
                                AlertDialog.Builder success = new AlertDialog.Builder(quanlykhachhang.this);
                                success.setTitle("Thành công!");
                                success.setMessage("Đã cập nhật khách hàng!");
                                success.setPositiveButton("OK", null);
                                success.show();
                                getlistview_khachhang();
                            }
                        });
                        ab.setNegativeButton("Không", null);
                        ab.show();
                    }
                }
            });
            dl.show();
        }

        if (item.getItemId()==R.id.item_xoa)
        {
            AlertDialog.Builder ab = new AlertDialog.Builder(quanlykhachhang.this);
            ab.setTitle("Xóa !");
            ab.setMessage("Xóa khách hàng này ?");
            ab.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dao.delete_khachhang(kh);
                    getlistview_khachhang();
                    AlertDialog.Builder success = new AlertDialog.Builder(quanlykhachhang.this);
                    success.setTitle("Thành công!");
                    success.setMessage("Đã xóa khách hàng!");
                    success.setPositiveButton("OK", null);
                    success.show();
                }
            });
            ab.setNegativeButton("Không", null);
            ab.show();

        }
        return super.onContextItemSelected(item);
    }

    public void getlistview_khachhang()
    {
        arr = dao.select_khachhang();
        adap = new Custom_adapter_qlkh(quanlykhachhang.this,R.layout.item_khachhang,arr);
        lv_qlkh.setAdapter(adap);
        adap.notifyDataSetChanged();
    }
}
