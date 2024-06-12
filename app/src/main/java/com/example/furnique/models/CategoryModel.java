package com.example.furnique.models;


import com.example.furnique.adapters.ProductCarouselAdapter;
import com.example.furnique.contracts.Constants;
import com.example.furnique.dto.response.ListResponseDTO;
import com.example.furnique.models.interfaces.CategoryAPI;
import com.example.furnique.schemas.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryModel {
    private List<Category> list;
    private ProductCarouselAdapter adapter;
    public CategoryModel(ProductCarouselAdapter productCarouselAdapter) {
        adapter = productCarouselAdapter;
        list = new ArrayList();
    }

    public List<Category> getList() {
        return list;
    }

    public void setList(List<Category> list) {
        this.list = list;
    }

    public void fetchCategories() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CategoryAPI categoryAPI = retrofit.create(CategoryAPI.class);
        categoryAPI.getCategories().enqueue(new Callback<ListResponseDTO<Category>>() {
            @Override
            public void onResponse(Call<ListResponseDTO<Category>> call, Response<ListResponseDTO<Category>> response) {
                setList(response.body().getData().getDocs());
                System.out.println("Success: Fetching categories " + getList().size());

                adapter.addProducts(list);
            }

            @Override
            public void onFailure(Call<ListResponseDTO<Category>> call, Throwable t) {
                System.out.println("Error when fetching categories");
                System.out.println(t);
            }
        });
    }
}
