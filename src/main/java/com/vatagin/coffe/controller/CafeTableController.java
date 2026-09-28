package com.vatagin.coffe.controller;

import com.vatagin.coffe.domain.CafeTable;
import com.vatagin.coffe.dto.request.CreateCafeTableRequest;
import com.vatagin.coffe.dto.response.CafeTableResponse;
import com.vatagin.coffe.service.CafeTableService;
import com.vatagin.coffe.service.QrCodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tables")
@RequiredArgsConstructor
public class CafeTableController {
    private final CafeTableService tableService;

    @GetMapping
    public List<CafeTableResponse> getAllByCafe(@RequestParam Long cafeId) {
        return tableService.getAllByCafe(cafeId);
    }

    @GetMapping("/{id}")
    public CafeTableResponse getById(@PathVariable Long id) {
        return tableService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CafeTableResponse create(@Valid @RequestBody CreateCafeTableRequest request) {
        return tableService.create(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        tableService.delete(id);
    }

    private final QrCodeService qrCodeService;

    @GetMapping(value = "/{tableId}/qr", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getQr(@PathVariable Long tableId) {
        CafeTable table = tableService.getEntity(tableId);
        String url = "http://localhost:8080/qr/" + table.getQrToken();
        return qrCodeService.generateQrPng(url, 300);
    }
}
