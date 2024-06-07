package com.example.furnique.models;

import com.example.furnique.contracts.Constants;
import com.example.furnique.dto.response.ProductDetailsResponseDTO;
import com.example.furnique.models.interfaces.ProductAPI;
import com.example.furnique.schemas.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetailsModel {
    private Product details;

    public ProductDetailsModel(String id) {
        this.details = null;
        fetchDetails(id);
    }

    public Product getDetails() {
        return this.details;
    }

    public void setDetails(Product details) {
        this.details = details;
    }

    public void fetchDetails(String id) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProductAPI productAPI = retrofit.create(ProductAPI.class);
        productAPI.getProductDetails(id).enqueue(new Callback<ProductDetailsResponseDTO>() {
            @Override
            public void onResponse(Call<ProductDetailsResponseDTO> call, Response<ProductDetailsResponseDTO> response) {
                setDetails(response.body().getData());
                System.out.println("Success: Fetching product " + id);
            }

            @Override
            public void onFailure(Call<ProductDetailsResponseDTO> call, Throwable t) {
                System.out.println("Error when fetching products");
                System.out.println(t);
            }
        });
    }
}
