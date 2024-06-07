package com.example.furnique.models.interfaces;

import com.example.furnique.dto.request.SignInDTO;
import com.example.furnique.dto.response.SignInResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignInAPI {
    @POST("/auth/customer/login")
    Call<SignInResponseDTO> loginCustomer(@Body SignInDTO signInDTO);
}
