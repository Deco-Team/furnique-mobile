package com.example.furnique.dto.response;

public class SignInResponseDTO {
    TokenResponseDTO data;

    public SignInResponseDTO(TokenResponseDTO data) {
        this.data = data;
    }

    public TokenResponseDTO getData() {
        return data;
    }

    public class TokenResponseDTO {
        private String accessToken;

        private String refreshToken;

        public String getAccessToken() {
            return accessToken;
        }
    }
}
