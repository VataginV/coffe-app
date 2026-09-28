package com.vatagin.coffe.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record PublicMenuResponse(
        Long cafeId,
        String cafeName,
        List<Category> categories
) {
    public record Category(
            Long id,
            String name,
            Integer sortOrder,
            List<Item> items
    ) {}

    public record Item(
            Long id,
            String name,
            String description,
            BigDecimal price,
            String photoUrl
    ) {}
}