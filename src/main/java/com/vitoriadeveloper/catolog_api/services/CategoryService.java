package com.vitoriadeveloper.catolog_api.services;

import com.vitoriadeveloper.catolog_api.domain.Category;
import com.vitoriadeveloper.catolog_api.domain.exceptions.CategoryNotFoundException;
import com.vitoriadeveloper.catolog_api.dto.AwsMessageCategoryDTO;
import com.vitoriadeveloper.catolog_api.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository repository;
    private final AwsSnsService awsSnsService;

    // TO DO dto aws p category
    public CategoryService(CategoryRepository repository, AwsSnsService awsSnsService) {
        this.repository = repository;
        this.awsSnsService = awsSnsService;
    }

    public List<Category> list() {
        return repository.findAll();
    }

    public Category create(Category category) {
        Category created = this.repository.save(category);
        AwsMessageCategoryDTO message = new AwsMessageCategoryDTO(
                created.getId(),
                created.getTitle(),
                created.getDescription(),
                created.getOwnerId()
        );

        awsSnsService.publishCategory(message);
        return created;
    }

    public Optional<Category> update(Category category, String id) {
        return this.repository.findById(id).map(existingCategory -> {
            existingCategory.setTitle(category.getTitle());
            existingCategory.setDescription(category.getDescription());
            Category updated = repository.save(existingCategory);
            AwsMessageCategoryDTO message = new AwsMessageCategoryDTO(
                    existingCategory.getId(),
                    existingCategory.getTitle(),
                    existingCategory.getDescription(),
                    existingCategory.getOwnerId()
            );

            awsSnsService.publishCategory(message);
            return updated;
        });
    }

    public void deleteById(String id) {
        if (!repository.existsById(id)) {
            throw new CategoryNotFoundException(id);
        }
        repository.deleteById(id);
        awsSnsService.publishCategory(new AwsMessageCategoryDTO(id, null, null, null));
    }
}
