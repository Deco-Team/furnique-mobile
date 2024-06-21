package com.example.furnique.dto.response;

public class DataResponseDTO<T> {
    T data;

    public DataResponseDTO(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
