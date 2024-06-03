package com.example.furnique.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.furnique.R;
import com.example.furnique.databinding.ActivitySignUpBinding;
import com.example.furnique.dto.request.SignUpDTO;
import com.example.furnique.models.SignUpModel;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.firstNameInput.requestFocus();
        binding.customBar.txtTitle.setText("Sign Up");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.signUpButton.setOnClickListener(v -> {
            SignUpDTO signUpDTO = new SignUpDTO();
            signUpDTO.setFirstName(binding.firstNameInput.getText().toString());
            signUpDTO.setLastName(binding.lastNameInput.getText().toString());
            signUpDTO.setEmail(binding.emailInputSignup.getText().toString());
            signUpDTO.setPassword(binding.passwordInputSignup.getText().toString());
            if(signUpDTO.getFirstName().isEmpty()){
                binding.firstNameInput.setError("Please enter your first name");
            } else if (signUpDTO.getLastName().isEmpty()){
                binding.lastNameInput.setError("Please enter your last name");
            } else if (signUpDTO.getEmail().isEmpty()){
                binding.emailInputSignup.setError("Please enter your email");
            } else if (signUpDTO.getPassword().isEmpty()){
                binding.passwordInputSignup.setError("Please enter your password");
            } else {
                SignUpModel signUpModel = new SignUpModel(signUpDTO);
                Intent intent = new Intent(this, Home.class);
                startActivity(intent);
            }

        });
    }
}