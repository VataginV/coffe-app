package com.vatagin.coffe.controller;

import com.vatagin.coffe.dto.request.CreateCafeRequest;
import com.vatagin.coffe.dto.response.CafeResponse;
import com.vatagin.coffe.service.CafeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cafes")
@RequiredArgsConstructor
public class CafeController {
    private final CafeService cafeService;

    @GetMapping
    public List<CafeResponse> getAll() {
        return cafeService.getAll();
    }

    @GetMapping("/{id}")
    public CafeResponse getById(@PathVariable Long id) {
        return cafeService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CafeResponse create(@Valid @RequestBody CreateCafeRequest request) {
        return cafeService.create(request);
    }

    @PutMapping("/{id}")
    public CafeResponse update(@PathVariable Long id, @Valid @RequestBody CreateCafeRequest request) {
        return cafeService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cafeService.delete(id);
    }
}
