package com.example.do_an.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.do_an.R;
import com.example.do_an.adapter.DataAdapter;
import com.example.do_an.model.MenuCollection;
import com.example.do_an.ui.DataPackageDetailActivity;

import java.util.ArrayList;
import java.util.List;


public class DataFragment extends Fragment {
    private RecyclerView recycle_data, recyclerView;
    private ImageButton btnBack1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);

        btnBack1 = view.findViewById(R.id.btnBack1);
        recyclerView = view.findViewById(R.id.recyclerView);
        setupRecycleView();

        btnBack1.setOnClickListener(v -> requireActivity().onBackPressed());
        return view;
    }

    private void setupRecycleView() {
        List<MenuCollection> menuCollectionList = generateMenuCollectionList();
        DataAdapter adapter = new DataAdapter(menuCollectionList, requireContext(), (menuCollection, title) -> {
            Intent intent = new Intent(requireContext(), DataPackageDetailActivity.class);

            intent.putExtra("title", title);
            startActivity(intent);

        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private List<MenuCollection> generateMenuCollectionList() {
        List<MenuCollection> menuCollectionList = new ArrayList<>();
        menuCollectionList.add(new MenuCollection(1, "70.000 Đ", R.drawable.k70));
        menuCollectionList.add(new MenuCollection(2, "90.000 Đ", R.drawable.k90));
        menuCollectionList.add(new MenuCollection(3, "120.000 Đ", R.drawable.k120));
        menuCollectionList.add(new MenuCollection(4, "30.000 Đ", R.drawable.k30));
        menuCollectionList.add(new MenuCollection(5, "10.000 Đ", R.drawable.k10));
        menuCollectionList.add(new MenuCollection(6, "5.000 Đ", R.drawable.k5));
        menuCollectionList.add(new MenuCollection(7, "3.000 Đ", R.drawable.k3));
        menuCollectionList.add(new MenuCollection(8, "15.000 Đ", R.drawable.k15));
        return menuCollectionList;
    }
}

