package com.example.project_android_ck.Quanlylaptop;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_android_ck.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainLaptop extends Activity {
    RecyclerView recyclerView;
    LaptopAdapter adapter;
    List<Laptop> laptopList;

}
