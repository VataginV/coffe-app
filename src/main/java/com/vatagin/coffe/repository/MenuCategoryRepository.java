package com.vatagin.coffe.repository;

import com.vatagin.coffe.domain.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Long> {
    List<MenuCategory> findAllByCafeIdOrderBySortOrderAsc(Long cafeId);
}
