package com.vatagin.coffe.repository;

import com.vatagin.coffe.domain.CafeTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CafeTableRepository extends JpaRepository<CafeTable, Long> {
    Optional<CafeTable> findByQrToken(String qrToken);
    List<CafeTable> findAllByCafeId(Long cafeId);
}
