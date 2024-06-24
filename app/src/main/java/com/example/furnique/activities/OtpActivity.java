package com.example.furnique.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.furnique.R;
import com.google.firebase.FirebaseException;

import java.util.concurrent.TimeUnit;

public class OtpActivity extends AppCompatActivity {
    private EditText inputCode1, inputCode2, inputCode3, inputCode4, inputCode5, inputCode6;
    private TextView textMobile;
    private Button buttonVerify;
    private ProgressBar progressBar;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otp);

        init();
        setTextMobile();
        setupOTPInputs();
        setVerificationId();
        setListener();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void init() {
        inputCode1 = findViewById(R.id.inputCode1);
        inputCode2 = findViewById(R.id.inputCode2);
        inputCode3 = findViewById(R.id.inputCode3);
        inputCode4 = findViewById(R.id.inputCode4);
        inputCode5 = findViewById(R.id.inputCode5);
        inputCode6 = findViewById(R.id.inputCode6);

        textMobile = findViewById(R.id.txtMobile);
        buttonVerify = findViewById(R.id.buttonVerify);
        progressBar = findViewById(R.id.progressBar);
    }

    private void setListener() {
        buttonVerify.setOnClickListener(v -> {
            if (inputCode1.getText().toString().trim().isEmpty()
                    || inputCode2.getText().toString().trim().isEmpty()
                    || inputCode3.getText().toString().trim().isEmpty()
                    || inputCode4.getText().toString().trim().isEmpty()
                    || inputCode5.getText().toString().trim().isEmpty()
                    || inputCode6.getText().toString().trim().isEmpty()) {
                Toast.makeText(OtpActivity.this, "Vui lòng nhập đúng mã OTP", Toast.LENGTH_SHORT).show();
                return;
            }
            String code =
                    inputCode1.getText().toString() +
                            inputCode2.getText().toString() +
                            inputCode3.getText().toString() +
                            inputCode4.getText().toString() +
                            inputCode5.getText().toString() +
                            inputCode6.getText().toString();
            Toast.makeText(OtpActivity.this, code, Toast.LENGTH_SHORT).show();

//            if (verificationId != null) {
//                progressBar.setVisibility(View.VISIBLE);
//                buttonVerify.setVisibility(View.INVISIBLE);
//                PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
//                        verificationId,
//                        code
//                );
//
//                FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
//                        .addOnCompleteListener(task -> {
//                            progressBar.setVisibility(View.GONE);
//                            buttonVerify.setVisibility(View.VISIBLE);
//                            if (task.isSuccessful()) {
//                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                startActivity(intent);
//                            } else {
//                                Toast.makeText(VerifyOTPActivity.this, "The verification code entered was invalid", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//            }
        });
        findViewById(R.id.textResendOTP).setOnClickListener(v -> {
            //verify phone number
//            PhoneAuthOptions options =
//                    PhoneAuthOptions.newBuilder()
//                            .setPhoneNumber("+84" + getIntent().getStringExtra("mobile"))
//                            .setTimeout(60L, TimeUnit.SECONDS)
//                            .setActivity(this)
//                            .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                                @Override
//                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//
//                                }
//
//                                @Override
//                                public void onVerificationFailed(@NonNull FirebaseException e) {
//
//                                    Toast.makeText(VerifyOTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//
//                                @Override
//                                public void onCodeSent(@NonNull String newVerificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                    verificationId = newVerificationId;
//                                    Toast.makeText(VerifyOTPActivity.this, "OTP Sent", Toast.LENGTH_SHORT).show();
//                                }
//                            })
//                            .build();
//            PhoneAuthProvider.verifyPhoneNumber(options);
            Toast.makeText(OtpActivity.this, "Gửi lại mã OTP", Toast.LENGTH_SHORT).show();

        });
    }

    private void setVerificationId() {
        verificationId = getIntent().getStringExtra("verificationId");
    }

    /**
     * If Intent() getStringExtra == "mobile" -> startActivity(VerifyActivity),
     * (TextView) textMobile will be received value "user mobile number"
     */
    private void setTextMobile() {
        textMobile.setText(String.valueOf(
//                getIntent().getStringExtra("mobile")
                "email@gmail.com"
        ));
    }

    /**
     * When the edittext1 (inputCode1) was inserted, the cursor will be jump to the
     * next edittext (in this case it would be "inputCode2")
     */
    private void setupOTPInputs() {
        inputCode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputCode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputCode3.requestFocus();
                } else {
                    inputCode1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputCode4.requestFocus();
                } else {
                    inputCode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputCode5.requestFocus();
                } else {
                    inputCode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inputCode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputCode6.requestFocus();
                } else {
                    inputCode4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputCode6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputCode6.requestFocus();
                } else {
                    inputCode5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}