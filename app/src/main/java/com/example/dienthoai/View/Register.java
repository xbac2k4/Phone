package com.example.dienthoai.View;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dienthoai.Model.Response;
import com.example.dienthoai.Model.ResponseRegister;
import com.example.dienthoai.Model.User;
import com.example.dienthoai.R;
import com.example.dienthoai.Service.HttpRequest;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class Register extends AppCompatActivity {

    HttpRequest httpRequest;
    TextInputEditText edt_email, edt_password, edt_fullname, edt_address, edt_phone;
    Spinner spn_sex;
    Button btn_register;
    ArrayList<String> listSex = new ArrayList<>();
    String strSex;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        anhXa();
        userListener();
    }
    private void anhXa() {
        httpRequest = new HttpRequest();
        toolbar = findViewById(R.id.tool_bar);
        //
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        edt_fullname = findViewById(R.id.ed_fullname);
        edt_address = findViewById(R.id.edt_address);
        edt_phone = findViewById(R.id.edt_phone);
        spn_sex = findViewById(R.id.spn_sex);
        btn_register = findViewById(R.id.btn_register);
        //
    }
    private void spnSex() {
        listSex.add("Nam");
        listSex.add("Nữ");
        listSex.add("Khác");
        ArrayAdapter ad = new ArrayAdapter(
                Register.this,
                android.R.layout.simple_spinner_dropdown_item,
                listSex);
        spn_sex.setAdapter(ad);
    }
    private void userListener() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Register");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();;
            }
        });
        spnSex();
        spn_sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                strSex = listSex.get(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _email = edt_email.getText().toString().trim();
                String _password = edt_password.getText().toString().trim();
                String _fullname = edt_fullname.getText().toString().trim();
                String _address = edt_address.getText().toString().trim();
                String _sex = strSex;
                String _phone = edt_phone.getText().toString().trim();

                User user = new User(_email, _password, _fullname, _address, _sex, _phone);

                httpRequest.callAPI().register(user).enqueue(responseRegisterCallback);
            }
        });
    }
    Callback<Response<User>> responseUser = new Callback<Response<User>>() {
        @Override
        public void onResponse(Call<Response<User>> call, retrofit2.Response<Response<User>> response) {

        }

        @Override
        public void onFailure(Call<Response<User>> call, Throwable t) {
            Log.d(TAG, "onFailure: " + t.getMessage());
        }
    };
    Callback<ResponseRegister> responseRegisterCallback = new Callback<ResponseRegister>() {
        @Override
        public void onResponse(Call<ResponseRegister> call, retrofit2.Response<ResponseRegister> response) {
            if (response.isSuccessful()) {
                if (response.body().getEC() == 0) {
                    Toast.makeText(Register.this, response.body().getEM().toString() , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, Login.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("email", edt_email.getText().toString().trim());
                    bundle.putString("password", edt_password.getText().toString().trim());
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (response.body().getEC() == -3) {
                    Toast.makeText(Register.this, response.body().getEM().toString() , Toast.LENGTH_SHORT).show();
                } else if (response.body().getEC() == -2) {
                    Toast.makeText(Register.this, response.body().getEM().toString() , Toast.LENGTH_SHORT).show();
                }
            } else {
                Log.d(TAG, "onResponse: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<ResponseRegister> call, Throwable t) {

        }
    };
}