package com.example.dienthoai.Service;

import com.example.dienthoai.Model.Bill;
import com.example.dienthoai.Model.BillDetail;
import com.example.dienthoai.Model.Manufacturer;
import com.example.dienthoai.Model.Phone;
import com.example.dienthoai.Model.Response;
import com.example.dienthoai.Model.ResponseBill;
import com.example.dienthoai.Model.ResponseData;
import com.example.dienthoai.Model.ResponseRegister;
import com.example.dienthoai.Model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    String ipv4 = "192.168.1.118";
    String DOMAIN = "http://"+ ipv4 +":8080/api/v1/";
    @POST("users/login")
    Call<Response<User>> login(@Body User user);

    @POST("users/register")
    Call<ResponseRegister> register(@Body User user);

    @GET("categories/")
    Call<Response<ResponseData<ArrayList<Phone>>>> getAllPhone(@Query("page") String page,
                                                               @Query("limit") String limit);

    @GET("categories/manufacturer")
    Call<Response<ResponseData<ArrayList<Phone>>>> getCategoriesByManufacturer(@Query("manufacturer") String manufacturer, @Query("page") String page,
                                                                 @Query("limit") String limit);
    @GET("manufacturers/")
    Call<Response<ArrayList<Manufacturer>>> getAllManufacturer();

    @POST("bills/")
    Call<ResponseBill> createBill(@Body Bill bill);
}
