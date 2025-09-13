package com.vitoriadeveloper.catolog_api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryResponseDTO {
    private String id;
    private String titulo;
    private String descricao;
}
