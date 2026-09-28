package com.vatagin.coffe.controller;

import com.vatagin.coffe.domain.CafeTable;
import com.vatagin.coffe.dto.response.GuestSessionResponse;
import com.vatagin.coffe.dto.response.PublicMenuResponse;
import com.vatagin.coffe.dto.response.QrInfoResponse;
import com.vatagin.coffe.service.CafeTableService;
import com.vatagin.coffe.service.GuestSessionService;
import com.vatagin.coffe.service.PublicMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/qr")
@RequiredArgsConstructor
public class QrController {

    private final CafeTableService tableService;
    private final PublicMenuService publicMenuService;
    private final GuestSessionService guestSessionService;

    /** Информация о кафе и столе по QR-токену. */
    @GetMapping("/{qrToken}")
    public QrInfoResponse getInfo(@PathVariable String qrToken) {
        CafeTable table = tableService.getByQrToken(qrToken);
        return new QrInfoResponse(
                table.getCafe().getId(),
                table.getCafe().getName(),
                table.getCafe().getAddress(),
                table.getId(),
                table.getTableNumber(),
                table.getSeats()
        );
    }

    /** Публичное меню кафе. */
    @GetMapping("/{qrToken}/menu")
    public PublicMenuResponse getMenu(@PathVariable String qrToken) {
        return publicMenuService.getMenuByQrToken(qrToken);
    }

    /** Открыть гостевую сессию (или вернуть уже открытую). */
    @PostMapping("/{qrToken}/sessions")
    @ResponseStatus(HttpStatus.CREATED)
    public GuestSessionResponse openSession(@PathVariable String qrToken) {
        return guestSessionService.openSession(qrToken);
    }
}