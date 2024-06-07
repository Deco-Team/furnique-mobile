package com.example.furnique.models;

import com.example.furnique.adapters.ProductCardAdapter;
import com.example.furnique.contracts.Constants;
import com.example.furnique.dto.response.ListResponseDTO;
import com.example.furnique.models.interfaces.ProductAPI;
import com.example.furnique.schemas.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductModel {
    private List<Product> list;
    private ProductCardAdapter adapter;
    public ProductModel(ProductCardAdapter productCardAdapter) {
        adapter = productCardAdapter;
        list = new ArrayList();
        fetchProducts();
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }

    public void fetchProducts() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ProductAPI productAPI = retrofit.create(ProductAPI.class);
        productAPI.getProducts().enqueue(new Callback<ListResponseDTO<Product>>() {
            @Override
            public void onResponse(Call<ListResponseDTO<Product>> call, Response<ListResponseDTO<Product>> response) {
                setList(response.body().getData().getDocs());
                System.out.println("Success: Fetching products " + getList().size());

                adapter.addProducts(list);
            }

            @Override
            public void onFailure(Call<ListResponseDTO<Product>> call, Throwable t) {
                System.out.println("Error when fetching products");
                System.out.println(t);
            }
        });
    }
}
