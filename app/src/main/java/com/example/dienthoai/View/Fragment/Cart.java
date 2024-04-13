package com.example.dienthoai.View.Fragment;

import static android.content.ContentValues.TAG;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dienthoai.Adapter.AdapterCart;
import com.example.dienthoai.Dao.DaoCart;
import com.example.dienthoai.Model.Bill;
import com.example.dienthoai.Model.BillDetail;
import com.example.dienthoai.Model.Phone;
import com.example.dienthoai.Model.ResponseBill;
import com.example.dienthoai.Model.User;
import com.example.dienthoai.R;
import com.example.dienthoai.Service.HttpRequest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart extends Fragment {
    RecyclerView rcv;
    AdapterCart adapterCart;
    DaoCart daoCart;
    ArrayList<com.example.dienthoai.Model.Cart> list;
    LinearLayoutManager linearLayoutManager;
    String idUser, fullname, phone, address;
    SharedPreferences sharedPreferences;
    TextView tv_clearall, tv_nocart;
    Button btn_checkout;
    ArrayList<BillDetail> billDetails = new ArrayList<>();
    HttpRequest httpRequest;
    String strCheckout = null;
    public Cart() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        anhXa(view);
        userListener();

        return view;
    }
    private void anhXa(View view) {
        httpRequest = new HttpRequest();
        btn_checkout = view.findViewById(R.id.btn_checkout);
        tv_clearall = view.findViewById(R.id.tv_clearall);
        tv_nocart = view.findViewById(R.id.tv_nocart);
        sharedPreferences = getContext().getSharedPreferences("INFO", Context.MODE_PRIVATE);
        rcv = view.findViewById(R.id.rcv_cart);
        list = new ArrayList<>();
        daoCart = new DaoCart(getContext());
        linearLayoutManager = new LinearLayoutManager(getContext());
    }
    private void userListener() {
        idUser = sharedPreferences.getString("id", "");
        fullname = sharedPreferences.getString("fullname", "");
        phone = sharedPreferences.getString("phone", "");
        address = sharedPreferences.getString("address", "");

        Log.d(TAG, "ID: " + idUser);
        list = daoCart.selectCartByIdUser(idUser);
        adapterCart = new AdapterCart(getContext(), list);
        rcv.setLayoutManager(linearLayoutManager);
        rcv.setAdapter(adapterCart);
        adapterCart.notifyDataSetChanged();

        if (list.size() == 0) {
            tv_nocart.setVisibility(View.VISIBLE);
        } else {
            tv_nocart.setVisibility(View.INVISIBLE);
        }

        tv_clearall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (daoCart.deleteAll(idUser)) {
                    list.clear();
                    adapterCart.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Cart has been cleared", Toast.LENGTH_SHORT).show();
                    tv_nocart.setVisibility(View.VISIBLE);
                }
            }
        });
        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.size() != 0) {
                    Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                    return;
                }
                User user = new User(idUser, fullname, address, phone);
                Log.d(TAG, "User: " + user.get_id() + " - " + user.getFullname()+ " - " + user.getAddress()+ " - " + user.getPhone());
                for (int i = 0; i < list.size(); i++) {
                    Phone phoneObj = new Phone(String.valueOf(list.get(i).getIdPhone()), String.valueOf(list.get(i).getName()), String.valueOf(list.get(i).getImage()));
                    BillDetail billDetail = new BillDetail();
                    billDetail.setPhone(phoneObj);
                    billDetail.setPrice(list.get(i).getPrice());
                    billDetail.setQuantity(list.get(i).getQuantity());
                    billDetails.add(billDetail);
                }
                OpenDialog_comfirm(user);
            }
        });
    }
    public void OpenDialog_comfirm(User user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.dialog_checkout, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        //
        TextView tv_submit = view.findViewById(R.id.tv_submit);
        TextView tv_cancle = view.findViewById(R.id.tv_cancel);
        Spinner spn_checkout = view.findViewById(R.id.spn_checkout);

        ArrayList<String> listT = new ArrayList<>();
        listT.add("Cash on Delivery");
        listT.add("Credit Card");
        listT.add("Bank Transfer");

        ArrayAdapter ad = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                listT);
        spn_checkout.setAdapter(ad);
        spn_checkout.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spn_checkout.getSelectedItemPosition() == i) {
                    strCheckout = listT.get(i).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (strCheckout == null) {
                    Toast.makeText(getContext(), "Chọn phương thức thanh toán", Toast.LENGTH_SHORT).show();
                    return;
                }
                Bill bill = new Bill(user, strCheckout, "Processing",billDetails);
                httpRequest.callAPI().createBill(bill).enqueue(new Callback<ResponseBill>() {
                    @Override
                    public void onResponse(Call<ResponseBill> call, Response<ResponseBill> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getEC() == 0) {
                                Toast.makeText(getContext(), "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                                daoCart.deleteAll(user.get_id());
                                list.clear();
                                adapterCart.notifyDataSetChanged();
                                tv_nocart.setVisibility(View.VISIBLE);
                                dialog.dismiss();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBill> call, Throwable t) {

                    }
                });
            }
        });
    }
}