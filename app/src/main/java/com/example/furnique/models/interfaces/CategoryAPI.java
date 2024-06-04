package com.example.furnique.models.interfaces;

import com.example.furnique.dto.response.ListResponseDTO;
import com.example.furnique.schemas.Category;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryAPI {
    @GET("/categories/customer?limit=50")
    Call<ListResponseDTO<Category>> getCategories();
}
