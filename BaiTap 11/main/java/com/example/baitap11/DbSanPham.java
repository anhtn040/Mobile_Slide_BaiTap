package com.example.baitap11;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbSanPham extends SQLiteOpenHelper {
    public DbSanPham(@Nullable Context context) {
        super(context, "dbSanPham", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table tbSanPham (ma text,ten text,soluong text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void ThemDL(SanPham sp) {
        SQLiteDatabase
                sqLiteDatabase = getWritableDatabase();
        String sql = "insert into tbSanPham values(?,?,?)";
        sqLiteDatabase.execSQL(sql, new String[]{sp.getMa(), sp.getTen(), sp.getSoluong()});
    }

    public ArrayList<SanPham> HienThiDL() {
        ArrayList<SanPham> data = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "select * from tbSanPham";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                SanPham sp = new SanPham();
                sp.setMa(cursor.getString(0));
                sp.setTen(cursor.getString(1));
                sp.setSoluong(cursor.getString(2));
                data.add(sp);
            } while (cursor.moveToNext());
        }
        return data;
    }

    public void SuaDL(SanPham sp) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "update tbSanPham set ten=?, soluong=? where ma=?";
        sqLiteDatabase.execSQL(sql, new String[]{sp.getTen(), sp.getSoluong(), sp.getMa()});
    }

    public void XoaDL(SanPham sp) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "delete from tbSanPham where ma=?";
        sqLiteDatabase.execSQL(sql, new String[]{sp.getMa()});
    }
}