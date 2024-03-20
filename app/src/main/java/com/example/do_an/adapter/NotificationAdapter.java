package com.example.do_an.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.do_an.R;
import com.example.do_an.model.ThongBaoModel;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ThongBaoViewHolder> {
    private List<ThongBaoModel> thongBaoList;

    // Constructor của Adapter, nhận vào danh sách các thông báo.
    public NotificationAdapter(List<ThongBaoModel> thongBaoList) {
        this.thongBaoList = thongBaoList;
    }

    @NonNull
    @Override
    public ThongBaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Tạo view mới từ XML layout cho mỗi item của RecyclerView.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thongbao, parent, false);
        return new ThongBaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongBaoViewHolder holder, int position) {
        // Gán dữ liệu vào ViewHolder.
        ThongBaoModel item = thongBaoList.get(position);
        holder.bind(item);
    }
    public void setNotifications(List<ThongBaoModel> newNotifications) {
        this.thongBaoList = newNotifications;
        notifyDataSetChanged(); // Thông báo cho RecyclerView rằng dữ liệu đã thay đổi
    }
    @Override
    public int getItemCount() {
        // Trả về kích thước của danh sách, bảo vệ khỏi trường hợp danh sách là null.
        return thongBaoList != null ? thongBaoList.size() : 0;
    }

    public class ThongBaoViewHolder extends RecyclerView.ViewHolder {
        // Khai báo các view trong layout của mỗi item.
        private TextView title_thongbao, sotiengiaodich, ngaygiaodich, giogiaodich;

        public ThongBaoViewHolder(@NonNull View itemView) {
            super(itemView);
            // Ánh xạ view từ XML.
            title_thongbao = itemView.findViewById(R.id.title_thongbao);
            sotiengiaodich = itemView.findViewById(R.id.sotiengiaodich);
            ngaygiaodich = itemView.findViewById(R.id.ngaygiaodich);
            giogiaodich = itemView.findViewById(R.id.giogiaodich);
        }

        // Cập nhật danh sách thông báo và thông báo cho adapter

        // Gán dữ liệu vào các view.
        public void bind(ThongBaoModel item){
            title_thongbao.setText(item.getTitle() + " thành công");
            sotiengiaodich.setText(item.getPrice());
            ngaygiaodich.setText(item.getDate());
            giogiaodich.setText(item.getHour());
        }
    }

}
