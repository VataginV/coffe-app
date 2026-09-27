package com.vatagin.coffe.repository;

import com.vatagin.coffe.domain.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findAllByCategoryId(Long categoryId);
    List<MenuItem> findAllByCategoryIdAndIsAvailableTrue(Long categoryId);
}