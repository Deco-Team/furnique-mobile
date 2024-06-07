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

import java.util.ArrayList;


public class ProductFragment extends Fragment {
    RecyclerView productRecycler;
    ArrayList<String> productList;
    ProductCardAdapter productCardAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        View customBar = view.findViewById(R.id.custom_bar);
        TextView txtTitle = customBar.findViewById(R.id.txtTitle);
        txtTitle.setText("Sản phẩm");

        productRecycler = view.findViewById(R.id.productRecycler);
        productList = new ArrayList<>();
        productList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
        productList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
        productList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
        productList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
        productList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
        productList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
        productList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
        productList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
        productList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
        productList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
        productList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
        productList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");

        productCardAdapter = new ProductCardAdapter(getContext(), productList);
        productRecycler.setAdapter(productCardAdapter);
        productRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));

        return view;
    }
}