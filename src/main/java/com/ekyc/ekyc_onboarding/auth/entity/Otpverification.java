package com.ekyc.ekyc_onboarding.auth.entity;


import com.ekyc.ekyc_onboarding.auth.OtpService.enums.OtpType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "otp_verification" , indexes = {
        @Index(name = "idx_otp_email" , columnList = "email" ),
        @Index(name = "idxs_otp_user" , columnList = "user_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Otpverification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false , length = 100)
    private String email;

    @Column(name = "otp_hash", nullable = false)
    private String otpHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false , length = 20)
    private OtpType type;

    @Column(name = "expires_at" , nullable = false)
    private LocalDateTime  expiresAt;

    @Column(name = "attempts" , nullable = false)
    @Builder.Default
    private int attempts = 0; // so lan nhap sai

    @Column(name = "max_attempts")
    @Builder.Default
    private int maxAttempts = 5;

    @Column(name = "verified")
    @Builder.Default
    private boolean verified = false;

    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expiresAt);
    }
    public boolean isExceededAttempts(){
        return attempts >= maxAttempts;
    }
    public boolean isUsable() {
        return !verified && !isExpired() && !isExceededAttempts();
    }







}

