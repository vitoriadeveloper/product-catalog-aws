package com.vitoriadeveloper.catolog_api.controllers;

import com.vitoriadeveloper.catolog_api.domain.Product;
import com.vitoriadeveloper.catolog_api.domain.exceptions.ProductNotFoundException;
import com.vitoriadeveloper.catolog_api.dto.ProductRequestDTO;
import com.vitoriadeveloper.catolog_api.dto.ProductResponseDTO;
import com.vitoriadeveloper.catolog_api.mappers.ProductMapper;
import com.vitoriadeveloper.catolog_api.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProductController {
    private final ProductService service;
    private final ProductMapper mapper;

    public ProductController(ProductService service, ProductMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> listAllProducts() {
        List<ProductResponseDTO> list = service.list()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> insertProduct(@Valid @RequestBody ProductRequestDTO request) {
        Product product = mapper.toEntity(request);
        Product created = service.create(product);
        return ResponseEntity
                .created(URI.create("/produtos/" + created.getId()))
                .body(mapper.toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable String id, @Valid ProductRequestDTO request) {
        Product updated = service.update(request, id).orElseThrow(() -> new ProductNotFoundException(id));
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
