package com.vatagin.coffe.mapper;

import com.vatagin.coffe.domain.MenuItem;
import com.vatagin.coffe.dto.request.CreateMenuItemRequest;
import com.vatagin.coffe.dto.response.MenuItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MenuItemMapper {

    @Mapping(target = "categoryId", source = "category.id")
    MenuItemResponse toResponse(MenuItem item);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "isAvailable", ignore = true)
    MenuItem toEntity(CreateMenuItemRequest request);
}