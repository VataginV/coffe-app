package com.vatagin.coffe.service;

import com.vatagin.coffe.domain.Cafe;
import com.vatagin.coffe.domain.MenuCategory;
import com.vatagin.coffe.dto.request.CreateMenuCategoryRequest;
import com.vatagin.coffe.dto.response.MenuCategoryResponse;
import com.vatagin.coffe.exception.NotFoundException;
import com.vatagin.coffe.mapper.MenuCategoryMapper;
import com.vatagin.coffe.repository.MenuCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuCategoryService {
    private final MenuCategoryRepository categoryRepository;
    private final CafeService cafeService;
    private final MenuCategoryMapper categoryMapper;

    public List<MenuCategoryResponse> getAllByCafe(Long cafeId) {
        return categoryRepository.findAllByCafeIdOrderBySortOrderAsc(cafeId).stream()
                .map(categoryMapper::toResponse)
                .toList();
    }

    public MenuCategoryResponse getById(Long id) {
        return categoryMapper.toResponse(getEntity(id));
    }

    @Transactional
    public MenuCategoryResponse create(CreateMenuCategoryRequest request) {
        Cafe cafe = cafeService.getEntity(request.cafeId());

        MenuCategory category = categoryMapper.toEntity(request);
        category.setCafe(cafe);
        if (category.getSortOrder() == null) {
            category.setSortOrder(0);
        }

        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    @Transactional
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("Category not found: " + id);
        }
        categoryRepository.deleteById(id);
    }

    /** Служебный метод — пригодится в MenuItemService */
    public MenuCategory getEntity(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found: " + id));
    }

//    public List<MenuCategoryResponse> getAllByCafe(Long cafeId) {
//        return categoryRepository.findAllByCafeIdOrderBySortOrderAsc(cafeId).stream()
//                .map(categoryMapper::toResponse)
//                .toList();
//    }
}
