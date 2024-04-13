package com.example.dienthoai.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dienthoai.Model.Phone;
import com.example.dienthoai.R;

public class DetailProducts extends AppCompatActivity {

    Toolbar toolbar;
    ImageView img_Image;
    TextView  tv_name, tv_description, tv_storageCapacity, tv_ram, tv_chipset, tv_display, tv_price, tv_discountPrice, tv_manufacturer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_products);

        anhXa();
        userListener();
        getIntentCategories();
    }
    private void anhXa() {
        toolbar = findViewById(R.id.tool_bar);
        img_Image = findViewById(R.id.img_Image);
        tv_name = findViewById(R.id.tv_name);
        tv_description = findViewById(R.id.tv_description);
        tv_storageCapacity = findViewById(R.id.tv_storagecapacity);
        tv_ram = findViewById(R.id.tv_ram);
        tv_chipset = findViewById(R.id.tv_chipset);
        tv_display = findViewById(R.id.tv_display);
        tv_price = findViewById(R.id.tv_price);
//        tv_discountPrice = findViewById(R.id.tv_di);
        tv_manufacturer = findViewById(R.id.tv_manufacturer);
    }
    private void getIntentCategories() {
        Bundle bundle = getIntent().getExtras();

        Phone phone = new Phone();
        phone.set_id(bundle.getString("id"));
        phone.setImage(bundle.getString("image"));
        phone.setName(bundle.getString("name"));
        phone.setDescription(bundle.getString("description"));
        phone.setStorageCapacity(bundle.getString("storageCapacity"));
        phone.setRam(bundle.getString("ram"));
        phone.setChipset(bundle.getString("chipset"));
        phone.setDisplay(bundle.getString("display"));
        phone.setPrice(bundle.getString("price"));
        phone.setDiscountPrice(bundle.getString("discountPrice"));
        phone.setManufacturer(bundle.getString("manufacturer"));
//                id, name, image, description, storageCapacity, ram, chipset, display, price, discountPrice, manufacturer;
        if (bundle != null) {
            if (phone.getImage() != "" ) {
                Glide.with(DetailProducts.this)
                        .load(phone.getImage())
                        .thumbnail(Glide.with(DetailProducts.this).load(R.drawable.noimageicon))
                        .into(img_Image);
            }
            tv_name.setText(phone.getName());
            tv_manufacturer.setText(phone.getManufacturer());
            tv_chipset.setText(phone.getChipset());
            tv_price.setText("$" + phone.getPrice());
            tv_display.setText(phone.getDisplay());
            tv_description.setText(phone.getDescription());
            tv_storageCapacity.setText("Storage Capacity: " + phone.getStorageCapacity());
        }
    }
    private void userListener() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detail Products");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();;
            }
        });
    }
}