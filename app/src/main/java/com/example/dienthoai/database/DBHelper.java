package com.example.dienthoai.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String dbName = "Cart";
    private static final int version = 1;

    public DBHelper(@Nullable Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tb_cart = "CREATE TABLE cart(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tensp TEXT NOT NULL, " +
                "giasp INTEGER NOT NULL, " +
                "soluong INTEGER NOT NULL, " +
                "tongtien INTEGER NOT NULL, " +
                "hinhanh TEXT NOT NULL, " +
                "iduser TEXT NOT NULL, " +
                "idphone TEXT NOT NULL)";
        db.execSQL(tb_cart);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if(i1 != i) {
            db.execSQL("DROP TABLE IF EXISTS tb_cart");
            onCreate(db);
        }
    }
}
