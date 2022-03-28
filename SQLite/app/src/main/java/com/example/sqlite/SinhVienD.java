package com.example.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class SinhVienD {
    public static ArrayList<SinhVien> getAll(Context context){
        ArrayList<SinhVien> ds = new ArrayList<>();
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs  = db.rawQuery("Select * from SinhVien ", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            int id = cs.getInt(0);
            String name = cs.getString(1);
            String contact = cs.getString(1);
            String address = cs.getString(1);
            String email = cs.getString(1);
            SinhVien sv = new SinhVien(id, name, contact, address, email);
            ds.add(sv);
        }
        cs.close();
        db.close();
        return ds;
    }
}
