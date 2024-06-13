package com.example.furnique.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furnique.R;
import com.example.furnique.adapters.ProductCardAdapter;
import com.example.furnique.adapters.ProductCarouselAdapter;
import com.example.furnique.models.CategoryModel;
import com.example.furnique.models.ProductModel;
import com.example.furnique.schemas.Category;
import com.example.furnique.schemas.Product;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView categoryRecycler;
    RecyclerView productRecycler;
    ArrayList<Category> categoryList;
    ArrayList<Product> productList;
    ProductCarouselAdapter productCarouselAdapter;
    ProductCardAdapter productCardAdapter;
    CategoryModel categoryModel;
    ProductModel productModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecycler = view.findViewById(R.id.categoryRecycler);
        productRecycler = view.findViewById(R.id.productRecycler);

        categoryList = new ArrayList<>();
        productList = new ArrayList<>();

        productCarouselAdapter = new ProductCarouselAdapter(getContext(), categoryList);
        categoryRecycler.setAdapter(productCarouselAdapter);

        productCardAdapter = new ProductCardAdapter(getContext(), productList);
        productRecycler.setAdapter(productCardAdapter);
        productRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));

        categoryModel = new CategoryModel(productCarouselAdapter);
        productModel = new ProductModel(productCardAdapter);
        categoryModel.fetchCategories();
        productModel.fetchHomeProducts();
        System.out.println("Categories: " + categoryModel.getList().size());
        System.out.println("Products: " + productModel.getList().size());

        productCarouselAdapter.setOnItemClickListener(new ProductCarouselAdapter.onItemClickListener() {
            @Override
            public void onClick(ImageView imageView, Category category) {
                // Handle item click here
            }
        });

        return view;
    }
}
