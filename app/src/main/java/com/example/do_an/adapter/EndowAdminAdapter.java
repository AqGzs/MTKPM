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
import com.example.do_an.model.UuDaiModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EndowAdminAdapter extends RecyclerView.Adapter<EndowAdminAdapter.UuDaiViewHolder> {
    private List<UuDaiModel> uuDaiList; // Danh sách các ưu đãi
    private Context context;

    // Constructor nhận danh sách ưu đãi và context
    public EndowAdminAdapter(List<UuDaiModel> uuDaiList, Context context) {
        this.uuDaiList = uuDaiList;
        this.context = context;
    }

    // Tạo ViewHolder mới khi cần thiết
    @NonNull
    @Override
    public UuDaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_uudai_admin, parent, false);
        return new UuDaiViewHolder(view);
    }

    // ViewHolder để giữ các thành phần giao diện của mỗi mục
    public static class UuDaiViewHolder extends RecyclerView.ViewHolder {
        TextView titleUuDaiTextView; // Tiêu đề ưu đãi
        TextView descriptionUuDaiTextView; // Mô tả ưu đãi
        ImageView imageUuDaiImageView; // Hình ảnh ưu đãi

        public UuDaiViewHolder(@NonNull View itemView) {
            super(itemView);
            titleUuDaiTextView = itemView.findViewById(R.id.title_ud_admin);
            descriptionUuDaiTextView = itemView.findViewById(R.id.description_ud_admin);
            imageUuDaiImageView = itemView.findViewById(R.id.img_ud_admin);
        }
    }

    // Thêm một ưu đãi mới vào danh sách và thông báo cho RecyclerView
    public void addUuDai(UuDaiModel newUuDai) {
        uuDaiList.add(newUuDai);
        notifyItemInserted(uuDaiList.size() - 1); // Thông báo cho RecyclerView biết rằng có dữ liệu mới được thêm vào vị trí cuối cùng
    }

    // Liên kết dữ liệu với ViewHolder
    @Override
    public void onBindViewHolder(@NonNull UuDaiViewHolder holder, int position) {
        UuDaiModel uuDai = uuDaiList.get(position);

        // Thiết lập tiêu đề và mô tả của ưu đãi
        holder.titleUuDaiTextView.setText(uuDai.getTenUuDai());
        holder.descriptionUuDaiTextView.setText(uuDai.getThongTinChiTiet());

        // Load hình ảnh từ URL bằng Picasso và đặt vào ImageView
        Picasso.get().load(uuDai.getImageUrl()).placeholder(R.drawable.momoimg).into(holder.imageUuDaiImageView);
    }

    // Trả về số lượng mục trong danh sách ưu đãi
    @Override
    public int getItemCount() {
        return uuDaiList.size();
    }

    // Cập nhật dữ liệu cho danh sách ưu đãi
    public void setData(List<UuDaiModel> uuDaiList) {
        this.uuDaiList = uuDaiList;
        notifyDataSetChanged(); // Thông báo cho RecyclerView biết dữ liệu đã thay đổi
    }
}
