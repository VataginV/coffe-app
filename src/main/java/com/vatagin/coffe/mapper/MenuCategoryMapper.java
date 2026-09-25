package com.vatagin.coffe.mapper;

import com.vatagin.coffe.domain.MenuCategory;
import com.vatagin.coffe.dto.request.CreateMenuCategoryRequest;
import com.vatagin.coffe.dto.response.MenuCategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MenuCategoryMapper {

    @Mapping(target = "cafeId", source = "cafe.id")
    MenuCategoryResponse toResponse(MenuCategory category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cafe", ignore = true)
    MenuCategory toEntity(CreateMenuCategoryRequest request);
}
