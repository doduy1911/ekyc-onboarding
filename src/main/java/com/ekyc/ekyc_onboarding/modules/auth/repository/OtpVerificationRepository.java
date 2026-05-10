package com.ekyc.ekyc_onboarding.modules.auth.repository;

import com.ekyc.ekyc_onboarding.common.enums.OtpType;
import com.ekyc.ekyc_onboarding.modules.auth.entity.Otpverification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface OtpVerificationRepository extends JpaRepository<Otpverification, UUID> {
    @Query("""
        select o from Otpverification o
            where o.user.id = :userid
                and o.type = :type
                and o.verified = false 
                and o.expiresAt > CURRENT_TIMESTAMP 
            order by o.createdAt DESC
            limit 1
    """)
    Optional<Otpverification> findLatestValidOtp(UUID userId, OtpType type);

    @Modifying
    @Query("delete from Otpverification o where o.user.id = :userId and o.type = :type and o.verified = false ")
    void deleteUnverifiedByUserAndType(UUID userId, OtpType type);


}
