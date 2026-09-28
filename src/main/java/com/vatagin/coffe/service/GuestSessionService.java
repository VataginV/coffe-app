package com.vatagin.coffe.service;

import com.vatagin.coffe.domain.CafeTable;
import com.vatagin.coffe.domain.GuestSession;
import com.vatagin.coffe.domain.SessionStatus;
import com.vatagin.coffe.dto.response.GuestSessionResponse;
import com.vatagin.coffe.exception.NotFoundException;
import com.vatagin.coffe.repository.GuestSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GuestSessionService {

    private final GuestSessionRepository sessionRepository;
    private final CafeTableService tableService;

    /**
     * Открыть сессию для стола.
     * Если для стола уже есть открытая сессия — вернуть её (не создавать новую).
     */
    @Transactional
    public GuestSessionResponse openSession(String qrToken) {
        CafeTable table = tableService.getByQrToken(qrToken);

        GuestSession session = sessionRepository
                .findByTableIdAndStatus(table.getId(), SessionStatus.OPEN)
                .orElseGet(() -> {
                    GuestSession newSession = GuestSession.builder()
                            .table(table)
                            .build();
                    return sessionRepository.save(newSession);
                });

        return toResponse(session);
    }

    /** Закрыть сессию. */
    @Transactional
    public GuestSessionResponse closeSession(Long sessionId) {
        GuestSession session = getEntity(sessionId);
        session.close();
        return toResponse(sessionRepository.save(session));
    }

    public GuestSession getEntity(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("GuestSession not found: " + id));
    }

    private GuestSessionResponse toResponse(GuestSession session) {
        CafeTable table = session.getTable();
        return new GuestSessionResponse(
                session.getId(),
                table.getId(),
                table.getTableNumber(),
                table.getCafe().getId(),
                table.getCafe().getName(),
                session.getStartedAt(),
                session.getClosedAt(),
                session.getStatus()
        );
    }
}