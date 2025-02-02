package com.example.furnique.models;

import android.util.Log;

import com.example.furnique.activities.SignUpActivity;
import com.example.furnique.contracts.Constants;
import com.example.furnique.dto.request.SignUpDTO;
import com.example.furnique.dto.response.SuccessResponseDTO;
import com.example.furnique.models.interfaces.SignUpAPI;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class SignUpModel {

    private final SignUpDTO signUpDTO;
    private final SignUpActivity signUpActivity;

    public SignUpModel(SignUpDTO signUpDTO, SignUpActivity signUpActivity) {
        this.signUpDTO = signUpDTO;
        this.signUpActivity = signUpActivity;
    }

    public void fetchSignUp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SignUpAPI signUpAPI = retrofit.create(SignUpAPI.class);
        signUpAPI.registerCustomer(signUpDTO).enqueue(new Callback<SuccessResponseDTO>() {
            @Override
            public void onResponse(Call<SuccessResponseDTO> call, Response<SuccessResponseDTO> response) {
                if(Objects.equals(response.message(), "Bad Request")){
                    signUpActivity.onSignUpFailure();
                } else {
                    signUpActivity.onSignUpSuccess();
                }
            }

            @Override
            public void onFailure(Call<SuccessResponseDTO> call, Throwable t) {
                Log.d("Response","t");
            }
        });
    }
}