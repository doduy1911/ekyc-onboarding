package com.ekyc.ekyc_onboarding.modules.auth.repository;

import com.ekyc.ekyc_onboarding.modules.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReFreshtokenRepository extends JpaRepository<RefreshToken , UUID> {
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    @Query("update RefreshToken rt SET rt.revoked = true , rt.revokedAt = CURRENT_TIMESTAMP where rt.user.id = :userId AND rt.revoked = false ")
    void revokeAllByUserId(UUID userId);

    @Modifying
    @Query("delete from RefreshToken rt where rt.expiresAt < CURRENT TIMESTAMP OR rt.revoked = true ")
    void deleteExpiredAndRevoked();

}
