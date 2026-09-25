package com.vatagin.coffe.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateCafeRequest(@NotBlank String name,
                                String address,
                                String phone) {
}
