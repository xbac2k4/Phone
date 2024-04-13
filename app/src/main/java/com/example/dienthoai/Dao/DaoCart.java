package com.example.dienthoai.Dao;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dienthoai.Model.Cart;
import com.example.dienthoai.database.DBHelper;

import java.util.ArrayList;

public class DaoCart {
    private final DBHelper dbHelper;

    public DaoCart(Context context) {
        dbHelper = new DBHelper(context);
    }
    public ArrayList<Cart> selectCartByIdUser(String iduser) {
        ArrayList<Cart> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
//        SQLiteDatabase dbb = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
        try {
            Cursor cursor =db.rawQuery("SELECT * FROM cart WHERE cart.iduser = ?", new String[] {iduser});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Cart cart = new Cart();
                    cart.set_id(cursor.getInt(0));
                    cart.setName(cursor.getString(1));
                    cart.setPrice(cursor.getInt(2));
                    cart.setQuantity(cursor.getInt(3));
                    cart.setTotalprice(cursor.getInt(4));
                    cart.setImage(cursor.getString(5));
                    cart.setIduser(cursor.getString(6));
                    cart.setIdPhone(cursor.getString(7));
                    list.add(cart);
                    cursor.moveToNext();
                }
            }

        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return list;
    }
    public boolean insert(Cart cart) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tensp", cart.getName());
        values.put("giasp", cart.getPrice());
        values.put("soluong", cart.getQuantity());
        values.put("tongtien", cart.getTotalprice());
        values.put("hinhanh", cart.getImage());
        values.put("iduser", cart.getIduser());
        values.put("idphone", cart.getIdPhone());
        long row = db.insert("cart", null, values);
        return (row > 0);
    }
    public boolean deleteAll(String idUser) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("cart", "iduser=?", new String[]{String.valueOf(idUser)});
        return (row > 0);
    }
    public boolean delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("cart", "id=?", new String[]{String.valueOf(id)});
        return (row > 0);
    }
}
