package com.vatagin.coffe.repository;

import com.vatagin.coffe.domain.GuestSession;
import com.vatagin.coffe.domain.SessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GuestSessionRepository extends JpaRepository<GuestSession, Long> {

    /** Найти открытую сессию для стола. */
    Optional<GuestSession> findByTableIdAndStatus(Long tableId, SessionStatus status);

    /** Все сессии для стола (например, история визитов). */
    List<GuestSession> findAllByTableId(Long tableId);
}