package com.vitoriadeveloper.catolog_api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryRequestDTO {
    private String titulo;
    private String descricao;
    private String donoId;
}
