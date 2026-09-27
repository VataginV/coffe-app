package com.vatagin.coffe.service;


import com.vatagin.coffe.domain.Cafe;
import com.vatagin.coffe.dto.request.CreateCafeRequest;
import com.vatagin.coffe.dto.response.CafeResponse;
import com.vatagin.coffe.exception.NotFoundException;
import com.vatagin.coffe.mapper.CafeMapper;
import com.vatagin.coffe.repository.CafeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CafeService {

    private final CafeRepository cafeRepository;
    private final CafeMapper cafeMapper;

    public List<CafeResponse> getAll() {
        return cafeRepository.findAll().stream()
                .map(cafeMapper::toResponse)
                .toList();
    }

    public CafeResponse getById(Long id) {
        return cafeRepository.findById(id)
                .map(cafeMapper::toResponse)
                .orElseThrow(() -> new NotFoundException("Cafe not found: " + id));
    }

    @Transactional
    public CafeResponse create(CreateCafeRequest request) {
        Cafe cafe = cafeMapper.toEntity(request);
        Cafe saved = cafeRepository.save(cafe);
        return cafeMapper.toResponse(saved);
    }

    @Transactional
    public CafeResponse update(Long id, CreateCafeRequest request) {
        Cafe cafe = cafeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cafe not found: " + id));
        cafe.setName(request.name());
        cafe.setAddress(request.address());
        cafe.setPhone(request.phone());
        return cafeMapper.toResponse(cafeRepository.save(cafe));
    }

    @Transactional
    public void delete(Long id) {
        if (!cafeRepository.existsById(id)) {
            throw new NotFoundException("Cafe not found: " + id);
        }
        cafeRepository.deleteById(id);
    }

    /** Служебный метод — пригодится в других сервисах (CafeTable, MenuCategory) */
    public Cafe getEntity(Long id) {
        return cafeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cafe not found: " + id));
    }
}
