package com.example.furnique.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.furnique.R;
import com.example.furnique.adapters.ProductCardAdapter;
import com.example.furnique.adapters.ProductCarouselAdapter;
import com.example.furnique.models.CategoryModel;
import com.example.furnique.models.ProductModel;
import com.example.furnique.schemas.Product;

import java.util.ArrayList;


public class ProductFragment extends Fragment {
    RecyclerView productRecycler;
    ArrayList<Product> productList;
    ProductCardAdapter productCardAdapter;
    ProductModel productModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        View customBar = view.findViewById(R.id.custom_bar);
        TextView txtTitle = customBar.findViewById(R.id.txtTitle);
        txtTitle.setText("Sản phẩm");

        productRecycler = view.findViewById(R.id.productRecycler);
        productList = new ArrayList<>();

        productCardAdapter = new ProductCardAdapter(getContext(), productList);
        productRecycler.setAdapter(productCardAdapter);
        productRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));

        productModel = new ProductModel(productCardAdapter);
        System.out.println("Products: " + productModel.getList().size());
        productRecycler.setAdapter(productCardAdapter);
        productRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        productModel.fetchProducts();
        return view;
    }
}