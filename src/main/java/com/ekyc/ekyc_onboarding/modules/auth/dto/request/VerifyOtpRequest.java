package com.ekyc.ekyc_onboarding.modules.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VerifyOtpRequest {
    @NotBlank(message = "Otp Không được để trống")
    @Size(min = 6 , max = 6 , message = "OTP phải đủ kí tự ")
    private String otp;
}
