package com.example.furnique.dto.response;

import com.example.furnique.schemas.CategoryList;

public class CategoryListResponseDTO {
    CategoryList data;

    public CategoryListResponseDTO(CategoryList data) {
        this.data = data;
    }

    public CategoryList getData() {
        return data;
    }
}
