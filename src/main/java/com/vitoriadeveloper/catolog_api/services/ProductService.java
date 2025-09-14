package com.vitoriadeveloper.catolog_api.services;

import com.vitoriadeveloper.catolog_api.domain.Category;
import com.vitoriadeveloper.catolog_api.domain.Product;
import com.vitoriadeveloper.catolog_api.domain.exceptions.CategoryNotFoundException;
import com.vitoriadeveloper.catolog_api.dto.ProductRequestDTO;
import com.vitoriadeveloper.catolog_api.mappers.ProductMapper;
import com.vitoriadeveloper.catolog_api.repositories.CategoryRepository;
import com.vitoriadeveloper.catolog_api.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository repository, CategoryRepository categoryRepository,  ProductMapper mapper) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    public List<Product> list() {
        return repository.findAll();
    }

    public Product create(Product product) {
        if (product.getCategory() != null && product.getCategory().getId() != null) {
            Category category = categoryRepository.findById(product.getCategory().getId())
                    .orElseThrow(() -> new CategoryNotFoundException(product.getCategory().getId()));
            product.setCategory(category);
        }
        return repository.save(product);
    }

    public Optional<Product> update(ProductRequestDTO dto, String id) {
        return repository.findById(id)
                .map(existingProduct -> {
                    // att o produto existente usando o mapper
                    mapper.updateProductFromDto(dto, existingProduct);

                    // se categoria foi enviada, busca a entidade completa
                    if (existingProduct.getCategory() != null && existingProduct.getCategory().getId() != null) {
                        Category category = categoryRepository.findById(existingProduct.getCategory().getId())
                                .orElseThrow(() -> new CategoryNotFoundException(existingProduct.getCategory().getId()));
                        existingProduct.setCategory(category);
                    }

                    return repository.save(existingProduct);
                });
    }

    public void deleteById(String id) {
        if (!repository.existsById(id)) {
            throw new CategoryNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
