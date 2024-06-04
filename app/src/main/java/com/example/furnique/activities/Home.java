package com.example.furnique.activities;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furnique.R;
import com.example.furnique.adapters.ProductCardAdapter;
import com.example.furnique.adapters.ProductCarouselAdapter;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    RecyclerView categoryRecycler;
    RecyclerView productRecycler;
    ArrayList<String> categoryList;
    ArrayList<String> productList;
    ProductCarouselAdapter productCarouselAdapter;
    ProductCardAdapter productCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        productRecycler = findViewById(R.id.productRecycler);

        categoryList = new ArrayList<>();
        categoryList.add("https://res.cloudinary.com/dpkfg05su/image/upload/20062264-69ec-4472-aed6-80d6c63a41f1.jpg");
        categoryList.add("https://res.cloudinary.com/dpkfg05su/image/upload/d9e266a3-95af-420b-972c-9028f056aa89.jpg");
        categoryList.add("https://res.cloudinary.com/dpkfg05su/image/upload/44dd00a4-8ac2-4d10-a4d7-0bf95b44e319.jpg");
        categoryList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");

        productList = new ArrayList<>();
        productList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
        productList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
        productList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");

        productCarouselAdapter = new ProductCarouselAdapter(this, categoryList);
        categoryRecycler.setAdapter(productCarouselAdapter);

        productCardAdapter = new ProductCardAdapter(this, productList);
        productRecycler.setAdapter(productCardAdapter);
        productRecycler.setLayoutManager(new GridLayoutManager(this, 2));


        productCarouselAdapter.setOnItemClickListener(new ProductCarouselAdapter.onItemClickListener() {
            @Override
            public void onClick(ImageView imageView, String path) {

            }
        });

    }
}