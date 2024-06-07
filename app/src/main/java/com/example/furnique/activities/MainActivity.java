package com.example.furnique.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.furnique.R;
import com.example.furnique.databinding.ActivityMainBinding;
import com.example.furnique.fragments.CartFragment;
import com.example.furnique.fragments.ContactFragment;
import com.example.furnique.fragments.HomeFragment;
import com.example.furnique.fragments.ProductFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnApplyWindowInsetsListener(null);
        binding.bottomNavigationView.setPadding(0,0,0,0);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if(R.id.home == item.getItemId()) {
                replaceFragment(new HomeFragment());
            }
            if(R.id.product == item.getItemId()) {
                replaceFragment(new ProductFragment());
            }
            if(R.id.cart == item.getItemId()) {
                replaceFragment(new CartFragment());
            }
            if(R.id.email == item.getItemId()) {
                replaceFragment(new ContactFragment());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}