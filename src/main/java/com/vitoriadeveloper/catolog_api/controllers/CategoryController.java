package com.vitoriadeveloper.catolog_api.controllers;

import com.vitoriadeveloper.catolog_api.domain.Category;
import com.vitoriadeveloper.catolog_api.domain.exceptions.CategoryNotFoundException;
import com.vitoriadeveloper.catolog_api.dto.CategoryRequestDTO;
import com.vitoriadeveloper.catolog_api.dto.CategoryResponseDTO;
import com.vitoriadeveloper.catolog_api.mappers.CategoryMapper;
import com.vitoriadeveloper.catolog_api.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoryController {
    private final CategoryService service;
    private final CategoryMapper mapper;

    public CategoryController(CategoryService service, CategoryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> listAllCategories() {
        List<CategoryResponseDTO> list = service.list()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> insertCategory(@Valid @RequestBody CategoryRequestDTO request) {
        Category category = mapper.toEntity(request);
        Category created = service.create(category);
        return ResponseEntity
                .created(URI.create("/categorias/" + created.getId()))
                .body(mapper.toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable String id, @Valid CategoryRequestDTO request) {
        Category category = mapper.toEntity(request);

        Category updated = service.update(category, id).orElseThrow(() -> new CategoryNotFoundException(id));

        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
