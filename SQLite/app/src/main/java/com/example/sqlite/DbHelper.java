package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context){
        super(context,"SQLite", null, 1);//tạo db
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table SinhVien(ID integer primary key autoincrement,"+
                        "Name text, Contact text, Address text, Email text)";
        db.execSQL(sql);
        sql = "Insert Into SinhVien VALUES (null, 'Nguyen Thanh Thinh', '0328132787', 'Da Nang', 'ntthinh.20it8@vku.udn.vn')";
        db.execSQL(sql);
        sql = "Insert Into SinhVien VALUES (null, 'Nguyen Van A', '0328132878', 'Quang Nam', 'nguyenvana@gmail.com')";
        db.execSQL(sql);
        sql = "Insert Into SinhVien VALUES (null, 'Nguyen Van B', '0328132888', 'Hue', 'nguyenvanb@gmail.com')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("Drop Table If exists SinhVien");
    onCreate(db);
    }
}
