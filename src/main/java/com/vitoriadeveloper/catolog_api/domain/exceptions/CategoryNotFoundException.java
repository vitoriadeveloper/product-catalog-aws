package com.vitoriadeveloper.catolog_api.domain.exceptions;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(String id){
        super("Categoria não encontrada para o id: " + id);
    }
}
