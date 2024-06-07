package com.example.furnique.models.interfaces;

import com.example.furnique.dto.request.SignUpDTO;
import com.example.furnique.dto.response.SuccessResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpAPI {
    @POST("/auth/customer/register")
    Call<SuccessResponseDTO> registerCustomer(@Body SignUpDTO signUpDTO);
}
