package com.example.furnique.models.interfaces;

import com.example.furnique.dto.response.CategoryListResponseDTO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryAPI {
    @GET("/categories/customer?limit=20")
    Call<CategoryListResponseDTO> getCategories();
}
