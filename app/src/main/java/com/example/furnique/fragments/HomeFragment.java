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

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView categoryRecycler;
    RecyclerView productRecycler;
    ArrayList<String> categoryList;
    ArrayList<String> productList;
    ProductCarouselAdapter productCarouselAdapter;
    ProductCardAdapter productCardAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecycler = view.findViewById(R.id.categoryRecycler);
        productRecycler = view.findViewById(R.id.productRecycler);

        categoryList = new ArrayList<>();
        categoryList.add("https://res.cloudinary.com/dpkfg05su/image/upload/20062264-69ec-4472-aed6-80d6c63a41f1.jpg");
        categoryList.add("https://res.cloudinary.com/dpkfg05su/image/upload/d9e266a3-95af-420b-972c-9028f056aa89.jpg");
        categoryList.add("https://res.cloudinary.com/dpkfg05su/image/upload/44dd00a4-8ac2-4d10-a4d7-0bf95b44e319.jpg");
        categoryList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");

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

        productCarouselAdapter = new ProductCarouselAdapter(getContext(), categoryList);
        categoryRecycler.setAdapter(productCarouselAdapter);

        productCardAdapter = new ProductCardAdapter(getContext(), productList);
        productRecycler.setAdapter(productCardAdapter);
        productRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));

        productCarouselAdapter.setOnItemClickListener(new ProductCarouselAdapter.onItemClickListener() {
            @Override
            public void onClick(ImageView imageView, String path) {
                // Handle item click here
            }
        });

        return view;
    }
}
