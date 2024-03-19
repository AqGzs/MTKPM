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

public class TransHisAdapter extends RecyclerView.Adapter<TransHisAdapter.ViewHolder> {
    private List<ThongBaoModel> danhSachGiaoDich; // Thay đổi tên biến để phản ánh ý nghĩa

    public TransHisAdapter(List<ThongBaoModel> danhSachGiaoDich) {
        this.danhSachGiaoDich = danhSachGiaoDich;
    }

    // Tạo ViewHolder mới khi cần thiết
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transhis, parent, false);
        return new ViewHolder(view);
    }

    // Liên kết dữ liệu với ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThongBaoModel giaoDich = danhSachGiaoDich.get(position);
        holder.bind(giaoDich);
    }

    // Trả về số lượng mục trong danh sách giao dịch
    @Override
    public int getItemCount() {
        return danhSachGiaoDich.size();
    }

    // ViewHolder để giữ các thành phần giao diện của mỗi mục
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tieuDeGiaoDich, giaTienGiaoDich, ngay, gio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tieuDeGiaoDich = itemView.findViewById(R.id.title_transhis);
            giaTienGiaoDich = itemView.findViewById(R.id.tien_transhis);
            ngay = itemView.findViewById(R.id.ngay_transhis);
            gio = itemView.findViewById(R.id.gio_transhis);
        }

        // Gán dữ liệu từ đối tượng ThongBaoModel vào các thành phần giao diện
        public void bind(ThongBaoModel giaoDich) {
            tieuDeGiaoDich.setText(giaoDich.getTitle() + " thành công");
            giaTienGiaoDich.setText(giaoDich.getPrice());
            ngay.setText(giaoDich.getDate());
            gio.setText(giaoDich.getHour());
        }
    }
}
