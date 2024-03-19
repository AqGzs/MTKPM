package com.example.do_an.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.do_an.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class UserInfoDataSource {
    private SQLiteDatabase database;
    private MyDatabaseHelper dbHelper;

    // Các cột trong bảng ThongTinCaNhan
    private String[] allColumns = {
            MyDatabaseHelper.ThongTinCaNhanTable.MA_TTCN,
            MyDatabaseHelper.ThongTinCaNhanTable.HO_TEN,
            MyDatabaseHelper.ThongTinCaNhanTable.EMAIL,
            MyDatabaseHelper.ThongTinCaNhanTable.GIOI_TINH,
            MyDatabaseHelper.ThongTinCaNhanTable.NGAY_SINH,
            MyDatabaseHelper.ThongTinCaNhanTable.CCCD_OR_CMND,
            MyDatabaseHelper.ThongTinCaNhanTable.DIA_CHI,
            MyDatabaseHelper.ThongTinCaNhanTable.MAT_KHAU
    };

    public UserInfoDataSource(Context context) {
        dbHelper = new MyDatabaseHelper(context);
    }

    // Mở kết nối đến cơ sở dữ liệu
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // Đóng kết nối đến cơ sở dữ liệu
    public void close() {
        dbHelper.close();
    }

    // Tạo một bản ghi mới trong bảng ThongTinCaNhan
    public UserInfo createUserInfo(String maTTCN, String hoTen, String email, String gioiTinh, String ngaySinh, String CCCD, String diaChi, int matKhau) {
        ContentValues values = new ContentValues();
        values.put(MyDatabaseHelper.ThongTinCaNhanTable.MA_TTCN, maTTCN);
        values.put(MyDatabaseHelper.ThongTinCaNhanTable.HO_TEN, hoTen);
        values.put(MyDatabaseHelper.ThongTinCaNhanTable.EMAIL, email);
        values.put(MyDatabaseHelper.ThongTinCaNhanTable.GIOI_TINH, gioiTinh);
        values.put(MyDatabaseHelper.ThongTinCaNhanTable.NGAY_SINH, ngaySinh);
        values.put(MyDatabaseHelper.ThongTinCaNhanTable.CCCD_OR_CMND, CCCD);
        values.put(MyDatabaseHelper.ThongTinCaNhanTable.DIA_CHI, diaChi);
        values.put(MyDatabaseHelper.ThongTinCaNhanTable.MAT_KHAU, matKhau);

        long insertId = database.insert(MyDatabaseHelper.ThongTinCaNhanTable.TABLE_NAME, null, values);

        if (insertId != -1) {
            Cursor cursor = database.query(MyDatabaseHelper.ThongTinCaNhanTable.TABLE_NAME,
                    allColumns, MyDatabaseHelper.ThongTinCaNhanTable.MA_TTCN + " = " + insertId, null,
                    null, null, null);
            cursor.moveToFirst();
            UserInfo newUserInfo = cursorToUserInfo(cursor);
            cursor.close();
            return newUserInfo;
        } else {
            // Xử lý trường hợp không thành công (tuỳ thuộc vào logic ứng dụng của bạn)
            return null;
        }
    }

    // Cập nhật thông tin của một người dùng
    public void updateUserInfo(UserInfo userInfo, String maTTCN, String hoTen, String email, String gioiTinh, String ngaySinh, String CCCD, String diaChi, int matKhau) {
        ContentValues values = new ContentValues();
        values.put(MyDatabaseHelper.ThongTinCaNhanTable.MA_TTCN, maTTCN);
        values.put(MyDatabaseHelper.ThongTinCaNhanTable.HO_TEN, hoTen);
        values.put(MyDatabaseHelper.ThongTinCaNhanTable.EMAIL, email);
        values.put(MyDatabaseHelper.ThongTinCaNhanTable.GIOI_TINH, gioiTinh);
        values.put(MyDatabaseHelper.ThongTinCaNhanTable.NGAY_SINH, ngaySinh);
        values.put(MyDatabaseHelper.ThongTinCaNhanTable.CCCD_OR_CMND, CCCD);
        values.put(MyDatabaseHelper.ThongTinCaNhanTable.DIA_CHI, diaChi);
        values.put(MyDatabaseHelper.ThongTinCaNhanTable.MAT_KHAU, matKhau);

        String whereClause = MyDatabaseHelper.ThongTinCaNhanTable.MA_TTCN + " = ?";
        String[] whereArgs = { maTTCN };
        database.update(MyDatabaseHelper.ThongTinCaNhanTable.TABLE_NAME, values, whereClause, whereArgs);
    }

    // Lấy tất cả thông tin người dùng từ cơ sở dữ liệu
    public List<UserInfo> getAllUserInfos() {
        List<UserInfo> userInfos = new ArrayList<>();
        Cursor cursor = database.query(MyDatabaseHelper.ThongTinCaNhanTable.TABLE_NAME,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            UserInfo userInfo = cursorToUserInfo(cursor);
            userInfos.add(userInfo);
            cursor.moveToNext();
        }
        cursor.close();
        return userInfos;
    }

    // Chuyển dữ liệu từ con trỏ sang đối tượng UserInfo
    private UserInfo cursorToUserInfo(Cursor cursor) {
        UserInfo userInfo = new UserInfo();
        userInfo.setMaTTCN(cursor.getString(0));
        userInfo.setHoTen(cursor.getString(1));
        userInfo.setEmail(cursor.getString(2));
        userInfo.setGioiTinh(cursor.getString(3));
        userInfo.setNgaySinh(cursor.getString(4));
        userInfo.setCCCD(cursor.getString(5));
        userInfo.setDiaChi(cursor.getString(6));
        userInfo.setMatKhau(cursor.getInt(7));
        return userInfo;
    }
}
