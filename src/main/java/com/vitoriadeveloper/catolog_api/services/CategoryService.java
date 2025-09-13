package com.vitoriadeveloper.catolog_api.services;

import com.vitoriadeveloper.catolog_api.domain.Category;
import com.vitoriadeveloper.catolog_api.domain.exceptions.CategoryNotFoundException;
import com.vitoriadeveloper.catolog_api.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> list() {
        return repository.findAll();
    }

    public Category create(Category category) {
        return this.repository.save(category);
    }

    public Optional<Category> update(Category category, String id) {
        return this.repository.findById(id).map(existingCategory -> {
            existingCategory.setTitle(category.getTitle());
            existingCategory.setDescription(category.getDescription());
            return repository.save(existingCategory);
        });
    }

    public void deleteById(String id) {
        if (!repository.existsById(id)) {
            throw new CategoryNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
