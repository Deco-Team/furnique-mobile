package com.example.furnique.models.interfaces;

import com.example.furnique.dto.request.SignInDTO;
import com.example.furnique.dto.request.VerifyDTO;
import com.example.furnique.dto.response.SignInResponseDTO;
import com.example.furnique.dto.response.SuccessResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignInAPI {
    @POST("/auth/customer/login-otp")
    Call<SuccessResponseDTO> loginCustomer(@Body SignInDTO signInDTO);

    @POST("/auth/customer/verify-otp")
    Call<SignInResponseDTO> verifyCustomer(@Body VerifyDTO verifyDTO);
}
