package com.example.do_an.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.do_an.R;
import com.example.do_an.model.MenuCollection;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    // Interface để xử lý sự kiện khi click vào một item
    public interface OnItemClickListener {
        void onItemClick(MenuCollection menuCollection, String title);
    }

    private Context context;
    private List<MenuCollection> lstMnCollection;
    private OnItemClickListener listener;

    public DataAdapter(List<MenuCollection> lstMnCollection, Context context, OnItemClickListener listener) {
        this.lstMnCollection = lstMnCollection;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.datalayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuCollection menuCollection = lstMnCollection.get(position);
        holder.imgCollection.setImageResource(menuCollection.getImg_menu_Collection());
        String titleCollection = menuCollection.getTitle_menuCollection();
        if (titleCollection.length() >= 29) {
            titleCollection = titleCollection.substring(0, 28) + "...";
        }
        holder.txtTitle.setText(titleCollection);

        // Sử dụng biến cuối cùng để tránh lỗi
        final String finalTitle = titleCollection;
        holder.itemView.setOnClickListener(v -> listener.onItemClick(menuCollection, finalTitle));
    }

    @Override
    public int getItemCount() {
        return lstMnCollection.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCollection;
        private TextView txtTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCollection = itemView.findViewById(R.id.image_data);
            txtTitle = itemView.findViewById(R.id.title_data);
        }
    }
}
