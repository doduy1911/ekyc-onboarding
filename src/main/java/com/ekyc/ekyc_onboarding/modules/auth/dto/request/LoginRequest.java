package com.ekyc.ekyc_onboarding.modules.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Username không được để trống ")
    private String username;

    @NotBlank(message = "Mật Khẩu không được để trống ")
    private String password;

    private String deviceInfo;
}
