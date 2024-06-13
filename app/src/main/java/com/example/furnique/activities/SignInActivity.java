package com.example.furnique.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.furnique.R;
import com.example.furnique.contracts.Constants;
import com.example.furnique.databinding.ActivityLoginBinding;
import com.example.furnique.dto.request.SignInDTO;
import com.example.furnique.models.SignInModel;

public class SignInActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.emailInput.requestFocus();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.loginButton.setOnClickListener(v -> {
            SignInDTO signInDTO = new SignInDTO();
            signInDTO.setEmail(binding.emailInput.getText().toString());
            signInDTO.setPassword(binding.passwordInput.getText().toString());
            if (signInDTO.getEmail().isEmpty()){
                binding.emailInput.setError("Vui lòng nhập email");
            } else if (signInDTO.getPassword().isEmpty()){
                binding.passwordInput.setError("Vui lòng nhập mật khẩu");
            } else {
                SignInModel signInModel = new SignInModel(this, signInDTO);
                signInModel.signIn();
            }
        });

        binding.registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });

        binding.customBar.btnBack.setOnClickListener(v -> {
            finish();
        });
    }

    public void onLoginSuccess(String accessToken) {
        Log.d("SignInActivity.onLoginSuccess", "Login success");
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.FURNIQUE_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("accessToken", accessToken);
        editor.commit();

        Intent intent = new Intent();
        setResult(2, intent);
        finish();
    }

    public void onLoginFailed() {
        Log.e("SignInActivity.onLoginSuccess", "Login failed");
        binding.txtError.setVisibility(View.VISIBLE);
        binding.txtError.setText("Email hoặc mật khẩu không đúng.");
    }
}