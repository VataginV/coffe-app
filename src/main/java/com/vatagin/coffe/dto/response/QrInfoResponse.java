package com.vatagin.coffe.dto.response;

public record QrInfoResponse(
        Long cafeId,
        String cafeName,
        String cafeAddress,
        Long tableId,
        String tableNumber,
        Integer seats
) {}