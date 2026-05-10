package com.ekyc.ekyc_onboarding.modules.auth.dto.response;

import com.ekyc.ekyc_onboarding.common.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private long expiresIn;
    private UserInfo  userInfo;
    @Data
    @Builder
    public static class UserInfo {
        private UUID id;
        private String username;
        private String email;
        private String fullName;
        private Role role;
    }
}
