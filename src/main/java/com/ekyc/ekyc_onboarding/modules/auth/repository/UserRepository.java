package com.ekyc.ekyc_onboarding.modules.auth.repository;

import com.ekyc.ekyc_onboarding.modules.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    // tự động tăng số lần thử thất bại
    @Modifying
    @Query("Update User u SET u.failedLoginAttempts = u.failedLoginAttempts + 1 where u.id = :userId")
    void incrementFailedAttempts(UUID userId);

    // Đặt lại số lần thử sai
    @Modifying
    @Query("update User u SET u.failedLoginAttempts = 0 , u.lockedUntil = null WHERE u.id = :userId")
    void resetFailedAttempts(UUID userId);
}