package com.example.furnique.activities;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furnique.R;
import com.example.furnique.adapters.ProductCardAdapter;
import com.example.furnique.adapters.ProductCarouselAdapter;
import com.example.furnique.models.CategoryModel;
import com.example.furnique.models.ProductModel;
import com.example.furnique.schemas.Product;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    RecyclerView categoryRecycler;
    RecyclerView productRecycler;
    ArrayList<String> categoryList;
    ArrayList<Product> productList;
    ProductCarouselAdapter productCarouselAdapter;
    ProductCardAdapter productCardAdapter;
    CategoryModel categoryModel;
    ProductModel productModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        categoryRecycler = findViewById(R.id.categoryRecycler);
        productRecycler = findViewById(R.id.productRecycler);

        categoryList = new ArrayList();
        productList = new ArrayList();

        productCarouselAdapter = new ProductCarouselAdapter(this, categoryList);
        categoryRecycler.setAdapter(productCarouselAdapter);

        productCardAdapter = new ProductCardAdapter(this, productList);
        productRecycler.setAdapter(productCardAdapter);
        productRecycler.setLayoutManager(new GridLayoutManager(this, 2));

        categoryModel = new CategoryModel(productCarouselAdapter);
        productModel = new ProductModel(productCardAdapter);
        System.out.println("Categories: " + categoryModel.getList().size());
        System.out.println("Products: " + productModel.getList().size());


        productCarouselAdapter.setOnItemClickListener(new ProductCarouselAdapter.onItemClickListener() {
            @Override
            public void onClick(ImageView imageView, String path) {
                System.out.println("productCarouselAdapter.setOnItemClickListener" + path);
            }
        });

    }
}