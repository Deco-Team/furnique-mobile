package com.example.furnique.dto.response;

public class SuccessResponseDTO {
    private boolean success;

    public SuccessResponseDTO(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
