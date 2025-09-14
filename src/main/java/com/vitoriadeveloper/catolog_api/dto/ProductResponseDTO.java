package com.vitoriadeveloper.catolog_api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponseDTO {
    private String id;

    private String titulo;
    private String dono;
    private Double preco;
    private String descricao;

    private CategoryResponseDTO categoria;
}
