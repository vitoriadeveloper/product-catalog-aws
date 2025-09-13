package com.vitoriadeveloper.catolog_api.repositories;

import com.vitoriadeveloper.catolog_api.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {}
