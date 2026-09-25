package com.vatagin.coffe.mapper;

import com.vatagin.coffe.domain.Cafe;
import com.vatagin.coffe.dto.request.CreateCafeRequest;
import com.vatagin.coffe.dto.response.CafeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CafeMapper {
    CafeResponse toResponse(Cafe cafe);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Cafe toEntity(CreateCafeRequest request);
}
