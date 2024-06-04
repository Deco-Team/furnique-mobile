package com.example.furnique.activities;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.furnique.R;

public class ProductCarousel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_carousel);

        ImageView imageView = findViewById(R.id.productCarousel);

        Glide.with(ProductCarousel.this).load(getIntent().getStringExtra("image")).into(imageView);
    }
}