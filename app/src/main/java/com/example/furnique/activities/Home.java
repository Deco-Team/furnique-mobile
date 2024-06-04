package com.example.furnique.activities;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furnique.R;
import com.example.furnique.adapters.ProductCarouselAdapter;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> productList;
    ProductCarouselAdapter productCarouselAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recycler);

        productList = new ArrayList<>();
        productList.add("https://res.cloudinary.com/dpkfg05su/image/upload/20062264-69ec-4472-aed6-80d6c63a41f1.jpg");
        productList.add("https://res.cloudinary.com/dpkfg05su/image/upload/d9e266a3-95af-420b-972c-9028f056aa89.jpg");
        productList.add("https://res.cloudinary.com/dpkfg05su/image/upload/44dd00a4-8ac2-4d10-a4d7-0bf95b44e319.jpg");
        productList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");

        productCarouselAdapter = new ProductCarouselAdapter(this, productList);
        recyclerView.setAdapter(productCarouselAdapter);

        productCarouselAdapter.setOnItemClickListener(new ProductCarouselAdapter.onItemClickListener() {
            @Override
            public void onClick(ImageView imageView, String path) {

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}