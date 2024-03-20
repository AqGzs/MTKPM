package com.example.do_an.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.do_an.DesignPattern.NotificationViewModel;
import com.example.do_an.R;
import com.example.do_an.adapter.NotificationAdapter;
import com.example.do_an.DesignPattern.FirestoreSingleton;
import com.example.do_an.model.ThongBaoModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    private NotificationViewModel viewModel;
    RecyclerView recyclerView;
    ImageButton btnNofBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongbao);
        FirebaseFirestore db = FirestoreSingleton.getInstance();
        btnNofBack = findViewById(R.id.btnNofBack);
        recyclerView = findViewById(R.id.recyclethongbao);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Ẩn ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.hide();

        // Setup RecyclerView
        recyclerView = findViewById(R.id.recyclethongbao);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NotificationAdapter adapter = new NotificationAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // Khởi tạo ViewModel
        viewModel = new ViewModelProvider(this).get(NotificationViewModel.class);

        // Quan sát LiveData từ ViewModel
        viewModel.getNotifications().observe(this, notifications -> {
            // Cập nhật dữ liệu vào adapter
            adapter.setNotifications(notifications);
        });

        btnNofBack = findViewById(R.id.btnNofBack);
        btnNofBack.setOnClickListener(v -> finish());
    }
}