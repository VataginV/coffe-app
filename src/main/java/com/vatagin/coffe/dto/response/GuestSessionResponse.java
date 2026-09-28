package com.vatagin.coffe.dto.response;

import com.vatagin.coffe.domain.SessionStatus;

import java.time.Instant;

public record GuestSessionResponse(
        Long id,
        Long tableId,
        String tableNumber,
        Long cafeId,
        String cafeName,
        Instant startedAt,
        Instant closedAt,
        SessionStatus status
) {}