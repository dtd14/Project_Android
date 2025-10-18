package com.example.project_android_ck;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainLaptop extends Activity {
    RecyclerView recyclerView;
    LaptopAdapter adapter;
    List<Laptop> laptopList;
    FloatingActionButton btnAdd;
    DatabaseHelper db;
    SearchView searchView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanlylaptop);
        db = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.btnAdd);
        searchView = findViewById(R.id.searchView);
        loadData();

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainLaptop.this, AddLaptop.class);
            startActivity(intent);
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void loadData() {
        laptopList = db.getAllLaptops();
        if (laptopList == null) {
            laptopList = new ArrayList<>();
        }
        adapter = new LaptopAdapter(this, laptopList, db);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        laptopList.clear();
        laptopList.addAll(db.getAllLaptops());
        adapter.notifyDataSetChanged();
    }

}
