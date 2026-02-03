package com.shermatov.carparts.repository;

import com.shermatov.carparts.domain.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByToken(UUID token);

    Optional<PasswordResetToken> findTopByUserIdAndUsedAtIsNullOrderByCreatedAtDesc(Long userId);

    Optional<PasswordResetToken> findByTokenAndUsedAtIsNull(UUID token);
}
