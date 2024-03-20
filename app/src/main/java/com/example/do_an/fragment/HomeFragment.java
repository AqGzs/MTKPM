package com.example.do_an.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.do_an.R;

import com.example.do_an.ui.TransferMoneyActivity;
import com.example.do_an.ui.DataActivity;

import com.example.do_an.ui.RechargeActivity;
import com.example.do_an.ui.NotificationActivity;
import com.example.do_an.ui.PersonalPageActivity;
import com.example.do_an.ui.RechargeActivity;
import com.example.do_an.ui.WithdrawMoneyActivity;
import com.example.do_an.ui.NotificationActivity;

import com.example.do_an.ui.TransferMoneyActivity;
import com.example.do_an.ui.WithdrawMoneyActivity;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;




public class HomeFragment extends Fragment {
    ImageButton goUsers, thongbao;
    TextView search, soduvi;
    LinearLayout naptien, rutTien, chuyenTien, napdata;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my_phone", Context.MODE_PRIVATE);
        String phoneNumber = sharedPreferences.getString("PHONE_NUMBER", "");
        goUsers = view.findViewById(R.id.goUsers);
        soduvi = view.findViewById(R.id.soduvi);
        naptien = view.findViewById(R.id.naptien);
        rutTien = view.findViewById(R.id.rutTien);
        chuyenTien = view.findViewById(R.id.chuyenTien);
        napdata = view.findViewById(R.id.napdata);
        thongbao = view.findViewById(R.id.thongbao);
        search = view.findViewById(R.id.search);
        getInfo(phoneNumber);
        setOnClickListeners();
        return view;
    }
    void getInfo(String phoneNumber){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Users").document(phoneNumber).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    long sodu = document.getLong("soDuVi");
                    if(sodu >= 1000)
                        soduvi.setText("Số dư ví: " + String.format("%,d", sodu) + "đ");
                    else
                        soduvi.setText(String.valueOf(sodu));
                }
            }
        });
    }

    void setOnClickListeners() {
        naptien.setOnClickListener(v -> startActivity(new Intent(getActivity(), RechargeActivity.class)));
        rutTien.setOnClickListener(v -> startActivity(new Intent(getActivity(), WithdrawMoneyActivity.class)));
        chuyenTien.setOnClickListener(v -> startActivity(new Intent(getActivity(), TransferMoneyActivity.class)));
        thongbao.setOnClickListener(v -> startActivity(new Intent(getActivity(), NotificationActivity.class)));
        napdata.setOnClickListener(v -> startActivity(new Intent(getActivity(), DataActivity.class)));
        goUsers.setOnClickListener(v -> startActivity(new Intent(getActivity(), PersonalPageActivity.class)));
    }
}
