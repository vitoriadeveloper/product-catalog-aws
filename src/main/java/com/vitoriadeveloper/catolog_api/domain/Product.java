package com.vitoriadeveloper.catolog_api.domain;

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

    private String title;
    private String owner;
    private Double price;
    private String description;

    private Category category;

}
