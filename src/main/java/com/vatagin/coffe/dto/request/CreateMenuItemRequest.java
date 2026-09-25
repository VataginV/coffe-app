package com.vatagin.coffe.dto.request;

import java.math.BigDecimal;

public record CreateMenuItemRequest(Long categoryId, String name, String description, BigDecimal price, String photoUrl) {
}
