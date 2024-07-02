package com.example.furnique.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.furnique.R;
import com.example.furnique.databinding.ActivityCheckoutBinding;
import com.example.furnique.databinding.ActivityLoginBinding;

public class CheckoutActivity extends AppCompatActivity {

    ActivityCheckoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_checkout);

        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.lastNameInputCheckout.requestFocus();

        binding.checkoutButton.setOnClickListener(v -> {
            String lastName = binding.lastNameInputCheckout.getText().toString();
            String firstName = binding.firstNameInputCheckout.getText().toString();
            String email = binding.emailIntputCheckout.getText().toString();
            String phone = binding.phoneInputCheckout.getText().toString();
            String address = binding.addressInputCheckout.getText().toString();

            if (lastName.isEmpty()) {
                binding.lastNameInputCheckout.setError("Vui lòng nhập họ");
            }
            if (firstName.isEmpty()) {
                binding.firstNameInputCheckout.setError();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}