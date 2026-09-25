package com.vatagin.coffe.dto.response;

import java.math.BigDecimal;

public record MenuItemResponse(Long id, Long categoryId, String name, String description, BigDecimal price, String photoUrl, Boolean isAvailable) {
}
