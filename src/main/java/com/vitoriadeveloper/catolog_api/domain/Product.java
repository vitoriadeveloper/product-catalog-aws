package com.vitoriadeveloper.catolog_api.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    private String id;

    @NotBlank(message = "O título é obrigatório")
    private String title;

    @NotBlank(message = "O restaurante dono é obrigatório")
    private String owner;

    @NotNull(message = "O preço é obrigatório para o produto")
    private Double price;

    @NotBlank(message = "A descrição do produto é obrigatória")
    private String description;

    private Category category;

}
