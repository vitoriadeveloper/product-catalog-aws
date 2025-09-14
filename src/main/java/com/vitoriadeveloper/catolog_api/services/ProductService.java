package com.vitoriadeveloper.catolog_api.services;

import com.vitoriadeveloper.catolog_api.domain.Product;
import com.vitoriadeveloper.catolog_api.domain.exceptions.CategoryNotFoundException;
import com.vitoriadeveloper.catolog_api.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> list() {
        return repository.findAll();
    }

    public Product create(Product product) {
        return this.repository.save(product);
    }

    public Optional<Product> update(Product product, String id) {
        return this.repository.findById(id).map(existingProduct -> {
            existingProduct.setTitle(product.getTitle());
            existingProduct.setPrice(product.getPrice());
            if (product.getCategory() != null) {
                existingProduct.setCategory(product.getCategory());
            }
            existingProduct.setDescription(product.getDescription());
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
