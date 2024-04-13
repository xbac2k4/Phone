package com.example.dienthoai.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;

import com.example.dienthoai.Model.User;
import com.example.dienthoai.R;
import com.example.dienthoai.View.Fragment.Cart;
import com.example.dienthoai.View.Fragment.Home;
import com.example.dienthoai.View.Fragment.Profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
//    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();
        userListener();
    }
    private void anhXa() {
        bottomNavigationView = findViewById(R.id.nav_bottom_bar);
//        toolbar = findViewById(R.id.toolbar);

    }
    private void userListener() {
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Home");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Fragment frgHome = new Home();
        replaceFrg(frgHome);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.nav_home) {
                    fragment = new Home();
//                    toolbar.setTitle(item.getTitle());
                    replaceFrg(fragment);
                } else if (item.getItemId() == R.id.nav_cart) {
                    fragment = new Cart();
//                    toolbar.setTitle(item.getTitle());
                    replaceFrg(fragment);
                } else if (item.getItemId() == R.id.nav_profile) {
                    fragment = new Profile();
//                    toolbar.setTitle(item.getTitle());
                    replaceFrg(fragment);
                }
                return false;
            }
        });
    }
    public void replaceFrg(Fragment frg) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, frg).commit();
    }

}