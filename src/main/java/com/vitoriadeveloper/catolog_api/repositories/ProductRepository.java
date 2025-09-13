package com.vitoriadeveloper.catolog_api.repositories;

import com.vitoriadeveloper.catolog_api.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {}
