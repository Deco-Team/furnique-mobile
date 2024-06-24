package com.example.furnique.dto.request;

public class VerifyDTO {
    private String email;
    private String otp;

    public VerifyDTO(String email, String otp) {
        this.email = email;
        this.otp = otp;
    }

    public VerifyDTO() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }

    public String getOTP() {
        return otp;
    }
}
