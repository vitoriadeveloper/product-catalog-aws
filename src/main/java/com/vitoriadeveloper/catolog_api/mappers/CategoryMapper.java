package com.vitoriadeveloper.catolog_api.mappers;

import com.vitoriadeveloper.catolog_api.domain.Category;
import com.vitoriadeveloper.catolog_api.dto.CategoryRequestDTO;
import com.vitoriadeveloper.catolog_api.dto.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    // mapping request
    @Mapping(source = "titulo", target = "title")
    @Mapping(source = "descricao", target = "description")
    @Mapping(source = "donoId", target = "ownerId")
    Category toEntity(CategoryRequestDTO dto);

    // mapping response
    @Mapping(source = "title", target = "titulo")
    @Mapping(source = "description", target = "descricao")
    CategoryResponse toDto(Category entity);
}
