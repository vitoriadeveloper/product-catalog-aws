package com.vitoriadeveloper.catolog_api.mappers;


import com.vitoriadeveloper.catolog_api.domain.Category;
import com.vitoriadeveloper.catolog_api.domain.Product;
import com.vitoriadeveloper.catolog_api.dto.ProductRequestDTO;
import com.vitoriadeveloper.catolog_api.dto.ProductResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class}, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface ProductMapper {

    @Mapping(source = "titulo", target = "title")
    @Mapping(source = "dono", target = "owner")
    @Mapping(source = "preco", target = "price")
    @Mapping(source = "descricao", target = "description")
    @Mapping(source = "categoriaId", target = "category", qualifiedByName = "mapCategoriaId")
    Product toEntity(ProductRequestDTO dto);


    @Mapping(source = "title", target = "titulo")
    @Mapping(source = "owner", target = "dono")
    @Mapping(source = "price", target = "preco")
    @Mapping(source = "description", target = "descricao")
    @Mapping(source = "category", target = "category")
    ProductResponseDTO toDto(Product entity);

    @Mapping(source = "titulo", target = "title")
    @Mapping(source = "dono", target = "owner")
    @Mapping(source = "preco", target = "price")
    @Mapping(source = "descricao", target = "description")
    @Mapping(source = "categoriaId", target = "category", qualifiedByName = "mapCategoriaId")
    void updateProductFromDto(ProductRequestDTO dto, @MappingTarget Product entity);

    @Named("mapCategoriaId")
    default Category mapCategoriaId(String categoriaId) {
        if (categoriaId == null) return null;
        Category c = new Category();
        c.setId(categoriaId);
        return c;
    }
}
