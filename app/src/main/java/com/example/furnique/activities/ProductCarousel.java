package com.example.furnique.activities;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.furnique.R;

public class ProductCarousel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_carousel);

        System.out.println("Hello");

        ImageView imageView = findViewById(R.id.productCarousel);
//        String imageUrl = "https://res.cloudinary.com/dpkfg05su/image/upload/20062264-69ec-4472-aed6-80d6c63a41f1.jpg";

        Glide.with(this)
                .load(getIntent().getStringExtra("image"))
                .apply(new RequestOptions()
                        .placeholder(R.drawable.logo) // Placeholder image
                        .error(R.drawable.ic_logout) // Error image in case of loading failure
                )
                .into(imageView);
    }
}