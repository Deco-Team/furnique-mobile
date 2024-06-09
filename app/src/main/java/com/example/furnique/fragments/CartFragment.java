package com.example.furnique.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.furnique.R;
import com.example.furnique.adapters.CartCardAdapter;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    RecyclerView cartRecycler;
    ArrayList<String> cartItemList;
    CartCardAdapter cartCardAdapter;
    LinearLayout layout_empty_cart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        View customBar = view.findViewById(R.id.custom_bar);
        TextView txtTitle = customBar.findViewById(R.id.txtTitle);
        txtTitle.setText("Giỏ hàng");

        cartRecycler = view.findViewById(R.id.rvCartItems);
        layout_empty_cart = view.findViewById(R.id.layout_empty_cart);

        cartItemList = new ArrayList<>();
        cartItemList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
        cartItemList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
        cartItemList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
        cartItemList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
        cartItemList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
//        cartItemList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
//        cartItemList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");


        if (!cartItemList.isEmpty()) {
            layout_empty_cart.setVisibility(View.INVISIBLE);
            cartCardAdapter = new CartCardAdapter(getContext(), cartItemList);
            cartRecycler.setAdapter(cartCardAdapter);
            cartRecycler.setLayoutManager(new GridLayoutManager(getContext(), 1));
        }

        return view;
    }
}