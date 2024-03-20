package com.example.do_an.DesignPattern;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.do_an.model.ThongBaoModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotificationViewModel extends ViewModel {
    private MutableLiveData<List<ThongBaoModel>> notifications;

    public LiveData<List<ThongBaoModel>> getNotifications() {
        if (notifications == null) {
            notifications = new MutableLiveData<>();
            loadNotifications();
        }
        return notifications;
    }

    private void loadNotifications() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("TransactionInfo")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<ThongBaoModel> list = new ArrayList<>();
                    queryDocumentSnapshots.forEach(documentSnapshot -> {
                        ThongBaoModel model = documentSnapshot.toObject(ThongBaoModel.class);
                        list.add(model);
                    });
                    Collections.sort(list, (o1, o2) -> o2.getDate().compareTo(o1.getDate()));
                    notifications.setValue(list);
                })
                .addOnFailureListener(e -> Log.e("NotificationViewModel", "Error loading notifications", e));
    }
}
