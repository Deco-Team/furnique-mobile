package com.example.furnique.dto.response;

import com.example.furnique.contracts.ListResponse;

public class ListResponseDTO<T> {
    ListResponse<T> data;

    public ListResponseDTO(ListResponse<T> data) {
        this.data = data;
    }

    public ListResponse<T> getData() {
        return data;
    }
}
