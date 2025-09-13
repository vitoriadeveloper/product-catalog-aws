package com.vitoriadeveloper.catolog_api.domain;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    private String id;

    @NotBlank(message = "O título é obrigatório")
    private String title;
    private String description;

    @NotBlank(message = "O donoId é obrigatório")
    private String ownerId;
}
