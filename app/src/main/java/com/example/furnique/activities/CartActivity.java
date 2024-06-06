package com.example.furnique.activities;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furnique.R;
import com.example.furnique.adapters.CartCardAdapter;
import com.example.furnique.adapters.ProductCardAdapter;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    RecyclerView cartRecycler;

    ArrayList<String> cartItemList;

    CartCardAdapter cartCardAdapter;

    LinearLayout layout_empty_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);

        cartRecycler = findViewById(R.id.rvCartItems);

        layout_empty_cart = findViewById(R.id.layout_empty_cart);


        cartItemList = new ArrayList<>();
        cartItemList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
        cartItemList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
        cartItemList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
//        cartItemList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
//        cartItemList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
//        cartItemList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");
//        cartItemList.add("https://res.cloudinary.com/dpkfg05su/image/upload/4eafe71a-1194-40ab-be88-0eb4aa91f172.jpg");


        if (!cartItemList.isEmpty()) {
            layout_empty_cart.setVisibility(View.INVISIBLE);
            cartCardAdapter = new CartCardAdapter(this, cartItemList);
            cartRecycler.setAdapter(cartCardAdapter);
            cartRecycler.setLayoutManager(new GridLayoutManager(this, 1));
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}