package com.example.dienthoai.View;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dienthoai.Model.Response;
import com.example.dienthoai.Model.User;
import com.example.dienthoai.R;
import com.example.dienthoai.Service.HttpRequest;
import com.example.dienthoai.databinding.ActivityLoginBinding;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;

public class Login extends AppCompatActivity {
    ActivityLoginBinding binding;
    HttpRequest httpRequest;

    TextInputEditText edt_email, edt_password;
    Button btn_login;
    TextView tv_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        anhXa();
        userListener();
        getIntentRegister();
    }

    private void anhXa() {
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        httpRequest = new HttpRequest();
        //
        btn_login = findViewById(R.id.btn_login);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        tv_register = findViewById(R.id.tv_register);
        //
    }
     private void getIntentRegister() {
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            edt_email.setText(bundle.getString("email"));
            edt_password.setText(bundle.getString("password"));
        }
     }
    private void userListener() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                String _email = edt_email.getText().toString().trim();
                String _password = edt_password.getText().toString().trim();
                user.setEmail(_email);
                user.setPassword(_password);
//                Toast.makeText(Login.this, "Email: " + _email + " - pass: " + _password, Toast.LENGTH_SHORT).show();
                httpRequest.callAPI().login(user).enqueue(responseUser);
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }
    Callback<Response<User>> responseUser = new Callback<Response<User>>() {
        @Override
        public void onResponse(Call<Response<User>> call, retrofit2.Response<Response<User>> response) {
            if (response.isSuccessful()) {
                if (response.body().getEC() == 0) {
                    Toast.makeText(Login.this, response.body().getEM().toString() , Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("INFO", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("id", response.body().getDT().get_id());
                    editor.putString("fullname", response.body().getDT().getFullname());
                    editor.putString("phone", response.body().getDT().getPhone());
                    editor.putString("address", response.body().getDT().getAddress());

                    editor.apply();
                    Log.d(TAG, "IDG: " + response.body().getDT().get_id());
                    Log.d(TAG, "IDG: " + response.body().getDT().getFullname());
                    Log.d(TAG, "IDG: " + response.body().getDT().getPhone());
                    Log.d(TAG, "IDG: " + response.body().getDT().getAddress());

                    startActivity(new Intent(Login.this, MainActivity.class));
                } else if (response.body().getEC() == 1) {
                    Toast.makeText(Login.this, response.body().getEM().toString() , Toast.LENGTH_SHORT).show();
                } else if (response.body().getEC() == -2) {
                    Toast.makeText(Login.this, response.body().getEM().toString() , Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public void onFailure(Call<Response<User>> call, Throwable t) {
            Log.d(TAG, "onFailure: " + t.getMessage());
        }
    };
}