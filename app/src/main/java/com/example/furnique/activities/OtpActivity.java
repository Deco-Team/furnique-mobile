package com.example.furnique.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.example.furnique.contracts.Constants;
import com.example.furnique.dto.request.SignInDTO;
import com.example.furnique.dto.request.VerifyDTO;
import com.example.furnique.models.VerifyModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class OtpActivity extends AppCompatActivity {
    private EditText inputCode1, inputCode2, inputCode3, inputCode4;
    private TextView textMobile;
    TextView txtVerifyError;
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

        txtVerifyError = findViewById(R.id.txtVerifyError);

        textMobile = findViewById(R.id.txtMobile);
        buttonVerify = findViewById(R.id.buttonVerify);
        progressBar = findViewById(R.id.progressBar);
    }

    private void setListener() {
        buttonVerify.setOnClickListener(v -> {
            if (inputCode1.getText().toString().trim().isEmpty() || inputCode2.getText().toString().trim().isEmpty() || inputCode3.getText().toString().trim().isEmpty() || inputCode4.getText().toString().trim().isEmpty()) {
                Toast.makeText(OtpActivity.this, "Vui lòng nhập mã OTP", Toast.LENGTH_SHORT).show();
                return;
            }
            String code = inputCode1.getText().toString() + inputCode2.getText().toString() + inputCode3.getText().toString() + inputCode4.getText().toString();

            VerifyDTO verifyDTO = new VerifyDTO();
            SignInDTO signInDTO = new SignInDTO();

            verifyDTO.setEmail(String.valueOf(
                    getIntent().getStringExtra("email")));
            verifyDTO.setOtp(code);
            VerifyModel verifyModel = new VerifyModel(this, verifyDTO, signInDTO);
            verifyModel.verify();

            progressBar.setVisibility(View.VISIBLE);
            buttonVerify.setVisibility(View.INVISIBLE);

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
//            Toast.makeText(OtpActivity.this, "Gửi lại mã OTP", Toast.LENGTH_SHORT).show();

            findViewById(R.id.textResendOTP).setVisibility(View.GONE);
            TextView txtTimer = findViewById(R.id.txtTimer);
            txtTimer.setVisibility(View.VISIBLE);
            new CountDownTimer(60000, 1000) {
                public void onTick(long millisUntilFinished) {
                    // Used for formatting digit to be in 2 digits only
                    NumberFormat f = new DecimalFormat("00");
                    long min = (millisUntilFinished / 60000) % 60;
                    long sec = (millisUntilFinished / 1000) % 60;
                    txtTimer.setText(f.format(min) + ":" + f.format(sec));
                }

                // When the task is over it will print 00:00:00 there
                public void onFinish() {
                    txtTimer.setText("00:00");
                    findViewById(R.id.textResendOTP).setVisibility(View.VISIBLE);
                    txtTimer.setVisibility(View.GONE);
                }
            }.start();

            String email = getIntent().getStringExtra("email");
            String password = getIntent().getStringExtra("password");

            VerifyDTO verifyDTO = new VerifyDTO();
            SignInDTO signInDTO = new SignInDTO();

            signInDTO.setEmail(email);
            signInDTO.setPassword(password);

            VerifyModel verifyModel = new VerifyModel(this, verifyDTO, signInDTO);
            verifyModel.resend();

        });
    }

    public void onVerifySuccess(String accessToken) {
        Log.d("OtpActivity.onVerifySuccess", "Verify success" + accessToken);
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Constants.FURNIQUE_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("accessToken", accessToken);
        editor.commit();

        progressBar.setVisibility(View.GONE);
        buttonVerify.setVisibility(View.VISIBLE);

        Intent intent = new Intent();
        setResult(2, intent);
        finish();
    }

    public void onVerifyFailed() {
        Log.e("OtpActivity.onVerifyFailed", "Verify failed");
        txtVerifyError.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        buttonVerify.setVisibility(View.VISIBLE);
        txtVerifyError.setText("Mã OTP không đúng");
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
                getIntent().getStringExtra("email"))
        );
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
                    inputCode4.requestFocus();
                } else {
                    inputCode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}