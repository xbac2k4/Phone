package com.example.dienthoai.View.Fragment;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dienthoai.Adapter.AdapterManufacturer;
import com.example.dienthoai.Adapter.AdapterPhone;
import com.example.dienthoai.Model.Manufacturer;
import com.example.dienthoai.Model.Phone;
import com.example.dienthoai.Model.Response;
import com.example.dienthoai.Model.ResponseData;
import com.example.dienthoai.Model.User;
import com.example.dienthoai.R;
import com.example.dienthoai.Service.HttpRequest;
import com.example.dienthoai.Service.OnClickListen;
import com.example.dienthoai.View.Login;
import com.example.dienthoai.View.Register;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class Home extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    HttpRequest httpRequest;
    AdapterPhone adapter;
    AdapterManufacturer adapterManufacturer;
    RecyclerView recyclerView;
    RecyclerView rcv_manufacturer;
    ArrayList<Phone> list;
    ArrayList<Phone> listS;
    int totalPages;
    ArrayList<Manufacturer> listManufacturer;
    SwipeRefreshLayout swipeRefreshLayout;
    private boolean isScrolling = false;
    int page;
    ImageButton btn_lui, btn_tien;
    TextView tv_sotrang;
    Boolean selectItem = false;
    Manufacturer manufacturerS;
    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        anhXa(view);
        userListener();
        fetchAPI();

        return view;
    }
    private void anhXa(View view) {
        httpRequest = new HttpRequest();
        recyclerView = view.findViewById(R.id.rcv);
        rcv_manufacturer = view.findViewById(R.id.rcv_manufacturer);
        list = new ArrayList<>();
        listS = new ArrayList<>();
        listManufacturer = new ArrayList<>();
        swipeRefreshLayout = view.findViewById(R.id.sf_data);
        btn_lui = view.findViewById(R.id.btn_lui);
        btn_tien = view.findViewById(R.id.btn_tien);
        tv_sotrang = view.findViewById(R.id.tv_sotrang);
    }
    private void userListener() {
        page = 1;
        swipeRefreshLayout.setOnRefreshListener(this);
        btn_tien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page > 0  && page < totalPages) {
                    page += 1;
                    if (selectItem == false) {
                        fetchAPI();
                    } else {
                        fetchAPICategoriesByManufacturer(manufacturerS);
                    }
                }
            }
        });
        btn_lui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page > 1 && page <= totalPages) {
                    page -= 1;
                    if (selectItem == false) {
                        fetchAPI();
                    } else {
                        fetchAPICategoriesByManufacturer(manufacturerS);
                    }
                }
            }
        });
    }
    private void fetchAPI() {
        swipeRefreshLayout.setRefreshing(true);
        httpRequest.callAPI().getAllManufacturer().enqueue(new Callback<Response<ArrayList<Manufacturer>>>() {
            @Override
            public void onResponse(Call<Response<ArrayList<Manufacturer>>> call, retrofit2.Response<Response<ArrayList<Manufacturer>>> response) {
                if (response.isSuccessful()) {
                    if (response.body().getEC() == 0) {
                        listManufacturer = response.body().getDT();
                        getDataManufacturer(listManufacturer);
                        fetchAPICategories(page);
                        Log.d(TAG, "onResponse: " + response.body().getEM());
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<ArrayList<Manufacturer>>> call, Throwable t) {

            }
        });
    }
    private void fetchAPICategoriesByManufacturer(Manufacturer manufacturer) {
        swipeRefreshLayout.setRefreshing(true);
        httpRequest.callAPI().getCategoriesByManufacturer( manufacturer.getName(), String.valueOf(page), String.valueOf(4)).enqueue(new Callback<Response<ResponseData<ArrayList<Phone>>>>() {
            @Override
            public void onResponse(Call<Response<ResponseData<ArrayList<Phone>>>> call, retrofit2.Response<Response<ResponseData<ArrayList<Phone>>>> response) {
                if (response.isSuccessful()) {
                    if (response.body().getEC() == 0) {
                        listS = response.body().getDT().getCategories();
                        totalPages = response.body().getDT().getTotalPages();
                        getData(listS);
                        Log.d(TAG, "onResponse: " + response.body().getEM());
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<ResponseData<ArrayList<Phone>>>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
    private void fetchAPICategories(int page) {
        httpRequest.callAPI().getAllPhone(String.valueOf(page), String.valueOf(4)).enqueue(new Callback<Response<ResponseData<ArrayList<Phone>>>>() {
            @Override
            public void onResponse(Call<Response<ResponseData<ArrayList<Phone>>>> call, retrofit2.Response<Response<ResponseData<ArrayList<Phone>>>> response) {
                if (response.isSuccessful()) {
                    if (response.body().getEC() == 0) {
                        list = response.body().getDT().getCategories();
                        totalPages = response.body().getDT().getTotalPages();
                        getData(list);
                        Log.d(TAG, "onResponse: " + response.body().getEM());
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<ResponseData<ArrayList<Phone>>>> call, Throwable t) {

            }
        });
    }
    private void getData(ArrayList<Phone> list) {
        adapter = new AdapterPhone(getContext(), list);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);
        tv_sotrang.setText(String.valueOf(page));
    }
    private void getDataManufacturer(ArrayList<Manufacturer> listM) {
        adapterManufacturer = new AdapterManufacturer(getContext(), listM);
        rcv_manufacturer.setAdapter(adapterManufacturer);
        adapterManufacturer.setOnClickListen(new OnClickListen() {
            @Override
            public void selectItem(Manufacturer manufacturer) {
                selectItem = true;
                page = 1;
                manufacturerS = manufacturer;
                fetchAPICategoriesByManufacturer(manufacturer);
            }
        });
    }
    @Override
    public void onRefresh() {
        page = 1;
        tv_sotrang.setText(String.valueOf(page));
        selectItem = false;
        fetchAPI();
    }
}