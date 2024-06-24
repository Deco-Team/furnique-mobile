package com.example.furnique.models;

import android.util.Log;

import com.example.furnique.activities.OtpActivity;
import com.example.furnique.contracts.Constants;
import com.example.furnique.dto.request.SignInDTO;
import com.example.furnique.dto.request.VerifyDTO;
import com.example.furnique.dto.response.SignInResponseDTO;
import com.example.furnique.dto.response.SuccessResponseDTO;
import com.example.furnique.models.interfaces.SignInAPI;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VerifyModel {

    private final VerifyDTO verifyDTO;
    private final SignInDTO signInDTO;

    private final OtpActivity otpActivity;


    public VerifyModel(OtpActivity otpActivity, VerifyDTO verifyDTO, SignInDTO signInDTO) {
        this.otpActivity = otpActivity;
        this.verifyDTO = verifyDTO;
        this.signInDTO = signInDTO;
    }

    public void verify() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SignInAPI signInAPI = retrofit.create(SignInAPI.class);
        signInAPI.verifyCustomer(verifyDTO).enqueue(new Callback<SignInResponseDTO>() {
            @Override
            public void onResponse(Call<SignInResponseDTO> call, Response<SignInResponseDTO> response) {
                Log.d("verify.onResponse: ", String.valueOf(response.code()));
                Log.d("verify.onResponse: ", new Gson().toJson(response.body()));
                if (response.code() == 201) {
                    Log.d("verify.onResponse: ", "Login Success");
                    otpActivity.onVerifySuccess(response.body().getData().getAccessToken());
                } else {
                    Log.d("verify.onResponse: ", "Invalid otp");
                    otpActivity.onVerifyFailed();
                }
            }

            @Override
            public void onFailure(Call<SignInResponseDTO> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    public void resend() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SignInAPI signInAPI = retrofit.create(SignInAPI.class);
        signInAPI.loginCustomer(signInDTO).enqueue(new Callback<SuccessResponseDTO>() {
            @Override
            public void onResponse(Call<SuccessResponseDTO> call, Response<SuccessResponseDTO> response) {
                Log.d("signIn.onResponse: ", String.valueOf(response.code()));
                Log.d("signIn.onResponse: ", new Gson().toJson(response.body()));
                if (response.code() == 201) {
                    Log.d("signIn.onResponse: ", "Success");
                } else {
                    Log.d("signIn.onResponse: ", "Invalid username or password");

                }
            }

            @Override
            public void onFailure(Call<SuccessResponseDTO> call, Throwable t) {
                System.out.println(t);
            }
        });
    }


}
