package com.example.do_an.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.do_an.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDataSource {
    private SQLiteDatabase database;
    private MyDatabaseHelper dbHelper;

    // Các cột của bảng TaiKhoan
    private String[] taiKhoanColumns = {
            MyDatabaseHelper.TaiKhoanTable.SO_TK,
            MyDatabaseHelper.TaiKhoanTable.MA_TTCN,
            MyDatabaseHelper.TaiKhoanTable.SO_DU_VI,
            MyDatabaseHelper.TaiKhoanTable.SO_DU_TUI_THAN_TAI
    };

    public UserDataSource(Context context) {
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

    // Tạo mới một tài khoản người dùng
    public User createUser(long soTK) {
        // Thêm giá trị vào bảng TaiKhoan
        ContentValues taiKhoanValues = new ContentValues();
        taiKhoanValues.put(MyDatabaseHelper.TaiKhoanTable.SO_TK, soTK);
        taiKhoanValues.put(MyDatabaseHelper.TaiKhoanTable.MA_TTCN, "CN" + soTK);
        taiKhoanValues.put(MyDatabaseHelper.TaiKhoanTable.SO_DU_VI, 0); // Thiết lập giá trị ban đầu cho SO_DU_VI
        taiKhoanValues.put(MyDatabaseHelper.TaiKhoanTable.SO_DU_TUI_THAN_TAI, 0); // Thiết lập giá trị ban đầu cho SO_DU_TUI_THAN_TAI
        long taiKhoanInsertId = database.insert(MyDatabaseHelper.TaiKhoanTable.TABLE_NAME, null, taiKhoanValues);

        // Tạo một đối tượng User mới với soTK và "CN" + soTK làm MaTTCN
        User newUser = new User();
        newUser.setId(soTK);
        newUser.setMaTTCN("CN" + soTK);
        newUser.setSoduvi(0); // Thiết lập giá trị ban đầu cho SO_DU_VI
        newUser.setSodutuithantai(0); // Thiết lập giá trị ban đầu cho SO_DU_TUI_THAN_TAI
        return newUser;
    }

    // Lấy tất cả người dùng từ cơ sở dữ liệu
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        Cursor cursor = database.query(MyDatabaseHelper.TaiKhoanTable.TABLE_NAME,
                taiKhoanColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            users.add(user);
            cursor.moveToNext();
        }
        cursor.close();

        return users;
    }

    // Chuyển dữ liệu từ con trỏ sang đối tượng User
    private User cursorToUser(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getLong(0));
        user.setMaTTCN(cursor.getString(1));
        user.setSoduvi(cursor.getLong(2));
        user.setSodutuithantai(cursor.getLong(3));
        return user;
    }
}
