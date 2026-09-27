package com.vatagin.coffe.service;

import com.vatagin.coffe.domain.MenuCategory;
import com.vatagin.coffe.domain.MenuItem;
import com.vatagin.coffe.dto.request.CreateMenuItemRequest;
import com.vatagin.coffe.dto.response.MenuItemResponse;
import com.vatagin.coffe.exception.NotFoundException;
import com.vatagin.coffe.mapper.MenuItemMapper;
import com.vatagin.coffe.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final MenuCategoryService categoryService;
    private final MenuItemMapper menuItemMapper;

    public List<MenuItemResponse> getAllByCategory(Long categoryId, boolean onlyAvailable) {
        List<MenuItem> items = onlyAvailable
                ? menuItemRepository.findAllByCategoryIdAndIsAvailableTrue(categoryId)
                : menuItemRepository.findAllByCategoryId(categoryId);

        return items.stream().map(menuItemMapper::toResponse).toList();
    }

    public MenuItemResponse getById(Long id) {
        return menuItemMapper.toResponse(getEntity(id));
    }

    @Transactional
    public MenuItemResponse create(CreateMenuItemRequest request) {
        MenuCategory category = categoryService.getEntity(request.categoryId());

        MenuItem item = menuItemMapper.toEntity(request);
        item.setCategory(category);
        if (item.getIsAvailable() == null) {
            item.setIsAvailable(true);
        }

        return menuItemMapper.toResponse(menuItemRepository.save(item));
    }

    @Transactional
    public void delete(Long id) {
        if (!menuItemRepository.existsById(id)) {
            throw new NotFoundException("Menu item not found: " + id);
        }
        menuItemRepository.deleteById(id);
    }

    /** Служебный метод — пригодится в OrderService */
    public MenuItem getEntity(Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Menu item not found: " + id));
    }
}
