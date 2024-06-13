package com.example.furnique.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.furnique.R;
import com.example.furnique.contracts.Constants;
import com.example.furnique.contracts.Utils;
import com.example.furnique.databinding.ActivityMainBinding;
import com.example.furnique.fragments.CartFragment;
import com.example.furnique.fragments.ContactFragment;
import com.example.furnique.fragments.HomeFragment;
import com.example.furnique.fragments.ProductFragment;

import org.json.JSONObject;

import java.sql.Timestamp;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.FURNIQUE_PREF, Context.MODE_PRIVATE);
        String accessToken = pref.getString("accessToken", null);
        Log.d("MainActivity.onCreate", "accessToken: " + accessToken);
        updateHeaderView(accessToken);

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

        binding.imgLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivityForResult(intent, 1);
        });

        binding.imgLogout.setOnClickListener(v -> {
            SharedPreferences furniquePref = getApplicationContext().getSharedPreferences(Constants.FURNIQUE_PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = furniquePref.edit();
            editor.remove("accessToken");
            editor.commit();
            binding.imgLogin.setVisibility(View.VISIBLE);
            binding.imgLogout.setVisibility(View.GONE);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("MainActivity.onActivityResult", "requestCode: " + requestCode + ", resultCode: " + resultCode);
        if(resultCode == 2) {
            binding.imgLogin.setVisibility(View.GONE);
            binding.imgLogout.setVisibility(View.VISIBLE);
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private void updateHeaderView(String token) {
        if(token != null) {
            try { //check valid accessToken
                String decodedString = Utils.decoded(token);
                JSONObject jsonObject = new JSONObject(decodedString);
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                if((jsonObject.getLong("exp") - timestamp.getTime() / 1000) > 0) {
                    //valid token
                    binding.imgLogin.setVisibility(View.GONE);
                    binding.imgLogout.setVisibility(View.VISIBLE);
                } else {
                    binding.imgLogin.setVisibility(View.VISIBLE);
                    binding.imgLogout.setVisibility(View.GONE);
                }
            } catch (Exception e) {
                Log.e("MainActivity.onCreate", "Decode access token failed");
                binding.imgLogin.setVisibility(View.VISIBLE);
                binding.imgLogout.setVisibility(View.GONE);
            }
            // logged in
        } else {
            binding.imgLogin.setVisibility(View.VISIBLE);
            binding.imgLogout.setVisibility(View.GONE);
        }
    }
}