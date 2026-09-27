package com.vatagin.coffe.service;
import com.vatagin.coffe.domain.Cafe;
import com.vatagin.coffe.domain.CafeTable;
import com.vatagin.coffe.dto.request.CreateCafeTableRequest;
import com.vatagin.coffe.dto.response.CafeTableResponse;
import com.vatagin.coffe.exception.NotFoundException;
import com.vatagin.coffe.mapper.CafeTableMapper;
import com.vatagin.coffe.repository.CafeTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CafeTableService {
    private final CafeTableRepository tableRepository;
    private final CafeService cafeService;  // ← используем другой сервис, чтобы не дублировать поиск Cafe
    private final CafeTableMapper tableMapper;

    public List<CafeTableResponse> getAllByCafe(Long cafeId) {
        return tableRepository.findAllByCafeId(cafeId).stream()
                .map(tableMapper::toResponse)
                .toList();
    }

    public CafeTableResponse getById(Long id) {
        return tableMapper.toResponse(getEntity(id));
    }

    @Transactional
    public CafeTableResponse create(CreateCafeTableRequest request) {
        Cafe cafe = cafeService.getEntity(request.cafeId());

        CafeTable table = tableMapper.toEntity(request);
        table.setCafe(cafe);
        table.setQrToken(UUID.randomUUID().toString()); // ← бизнес-логика!

        return tableMapper.toResponse(tableRepository.save(table));
    }

    @Transactional
    public void delete(Long id) {
        if (!tableRepository.existsById(id)) {
            throw new NotFoundException("Table not found: " + id);
        }
        tableRepository.deleteById(id);
    }

    /** Служебный метод — пригодится в QrController и OrderService */
    public CafeTable getEntity(Long id) {
        return tableRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Table not found: " + id));
    }

    public CafeTable getByQrToken(String qrToken) {
        return tableRepository.findByQrToken(qrToken)
                .orElseThrow(() -> new NotFoundException("Table not found by token: " + qrToken));
    }
}
