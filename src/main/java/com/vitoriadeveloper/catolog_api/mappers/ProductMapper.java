package com.vitoriadeveloper.catolog_api.mappers;


import com.vitoriadeveloper.catolog_api.domain.Product;
import com.vitoriadeveloper.catolog_api.dto.ProductRequestDTO;
import com.vitoriadeveloper.catolog_api.dto.ProductResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})

public interface ProductMapper {

    @Mapping(source = "titulo", target = "title")
    @Mapping(source = "dono", target = "owner")
    @Mapping(source = "preco", target = "price")
    @Mapping(source = "descricao", target = "description")
    @Mapping(source = "descricao", target = "description")
    @Mapping(source = "categoria", target = "category")
    Product toEntity(ProductRequestDTO dto);


    @Mapping(source = "title", target = "titulo")
    @Mapping(source = "owner", target = "dono")
    @Mapping(source = "price", target = "preco")
    @Mapping(source = "description", target = "descricao")
    @Mapping(source = "category", target = "categoria")
    ProductResponseDTO toDto(Product entity);
}
