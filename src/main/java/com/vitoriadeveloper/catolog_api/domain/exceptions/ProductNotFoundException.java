package com.vitoriadeveloper.catolog_api.domain.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String id) {
        super("Produto não encontrada para o id: " + id);
    }
}
