package com.example.furnique.activities;

import android.content.Intent;
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
        binding.customBar.txtTitle.setText("Đăng ký");

        binding.signUpButton.setOnClickListener(v -> {
            SignUpDTO signUpDTO = new SignUpDTO();
            signUpDTO.setFirstName(binding.firstNameInput.getText().toString());
            signUpDTO.setLastName(binding.lastNameInput.getText().toString());
            signUpDTO.setEmail(binding.emailInputSignup.getText().toString());
            signUpDTO.setPassword(binding.passwordInputSignup.getText().toString());
            if (signUpDTO.getFirstName().isEmpty()) {
                binding.firstNameInput.setError("Hãy nhập tên của bạn");
            } else if (signUpDTO.getLastName().isEmpty()) {
                binding.lastNameInput.setError("Hãy nhập tên họ của bạn");
            } else if (signUpDTO.getEmail().isEmpty()) {
                binding.emailInputSignup.setError("Hãy nhập email của bạn");
            } else if (!signUpDTO.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                binding.emailInputSignup.setError("Email không hợp lệ");
            }else if (signUpDTO.getPassword().isEmpty()) {
                binding.passwordInputSignup.setError("Hãy nhập mật khẩu của bạn");
            } else if (!signUpDTO.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,}).*")) {
                binding.passwordInputSignup.setError("Phải chứa 8 ký tự, một chữ hoa, một chữ thường, một số và một ký tự đặc biệt");
            } else {
                SignUpModel signUpModel = new SignUpModel(signUpDTO, this);
                signUpModel.fetchSignUp();

            }
        });

        binding.loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
        });
        binding.customBar.btnBack.setOnClickListener(v -> {
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onSignUpFailure() {
        binding.errSignUp.setText("Người dùng đã tồn tại");
    }
    public void onSignUpSuccess() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

}
