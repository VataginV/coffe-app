package com.vatagin.coffe.mapper;

import com.vatagin.coffe.domain.CafeTable;
import com.vatagin.coffe.dto.request.CreateCafeTableRequest;
import com.vatagin.coffe.dto.response.CafeTableResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CafeTableMapper {

    @Mapping(target = "cafeId", source = "cafe.id")
    CafeTableResponse toResponse(CafeTable table);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cafe", ignore = true)
    @Mapping(target = "qrToken", ignore = true)
    CafeTable toEntity(CreateCafeTableRequest request);
}
