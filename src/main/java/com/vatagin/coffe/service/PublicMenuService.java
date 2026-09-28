package com.vatagin.coffe.service;

import com.vatagin.coffe.domain.Cafe;
import com.vatagin.coffe.domain.CafeTable;
import com.vatagin.coffe.domain.MenuCategory;
import com.vatagin.coffe.domain.MenuItem;
import com.vatagin.coffe.dto.response.PublicMenuResponse;
import com.vatagin.coffe.repository.MenuCategoryRepository;
import com.vatagin.coffe.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicMenuService {

    private final CafeTableService tableService;
    private final MenuCategoryRepository categoryRepository;
    private final MenuItemRepository itemRepository;

    public PublicMenuResponse getMenuByQrToken(String qrToken) {
        CafeTable table = tableService.getByQrToken(qrToken);
        Cafe cafe = table.getCafe();

        List<MenuCategory> categories = categoryRepository
                .findAllByCafeIdOrderBySortOrderAsc(cafe.getId());

        List<PublicMenuResponse.Category> categoryDtos = categories.stream()
                .map(cat -> {
                    List<MenuItem> items = itemRepository
                            .findAllByCategoryIdAndIsAvailableTrue(cat.getId());
                    List<PublicMenuResponse.Item> itemDtos = items.stream()
                            .map(this::toItemDto)
                            .toList();
                    return new PublicMenuResponse.Category(
                            cat.getId(),
                            cat.getName(),
                            cat.getSortOrder(),
                            itemDtos
                    );
                })
                .toList();

        return new PublicMenuResponse(cafe.getId(), cafe.getName(), categoryDtos);
    }

    private PublicMenuResponse.Item toItemDto(MenuItem item) {
        return new PublicMenuResponse.Item(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getPhotoUrl()
        );
    }
}