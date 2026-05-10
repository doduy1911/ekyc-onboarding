package com.ekyc.ekyc_onboarding.modules.auth.entity;


import com.ekyc.ekyc_onboarding.common.enums.OtpType;
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

//    phân loại OTP
    @Enumerated(EnumType.STRING)
    @Column(nullable = false , length = 20)
    private OtpType type;

//    thời điểm mà otp hết hạn
    @Column(name = "expires_at" , nullable = false)
    private LocalDateTime  expiresAt;
// số lần mà người dung nhpj sai
    @Column(name = "attempts" , nullable = false)
    @Builder.Default
    private int attempts = 0; // so lan nhap sai

//    số lần tối đa người dùng được nhập sai
    @Column(name = "max_attempts")
    @Builder.Default
    private int maxAttempts = 5;

//    mã otp này đã được dùng hay chưa
    @Column(name = "verified")
    @Builder.Default
    private boolean verified = false;

//    mã otp này được dùng vào lúc nào
    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;
//  mã otp này được tạo lúc nào
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

//  so sánh nếu thời gian hiện tại mà lớn hơn time của otp thì trả true
    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expiresAt);
    }
//    kiểm tra nếu số lần thử mà lớn max thì trả true mà khóa
    public boolean isExceededAttempts(){
        return attempts >= maxAttempts;
    }
//    kiểm tra mã đã được sử dụng hay chưa
    public boolean isUsable() {
        return !verified && !isExpired() && !isExceededAttempts();
    }
}

