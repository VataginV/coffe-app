package com.vatagin.coffe.dto.request;

public record CreateMenuCategoryRequest(Long cafeId, String name, Integer sortOrder) {
}
