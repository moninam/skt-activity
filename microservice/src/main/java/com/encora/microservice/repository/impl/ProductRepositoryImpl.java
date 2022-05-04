package com.encora.microservice.repository.impl;

import com.encora.commons.dto.Product;
import com.encora.microservice.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProductRepositoryImpl implements ProductRepository {
    //TODO: DELETE THIS PART WHEN IMPLEMENTS STORED PROCEDURES
    private final Map<String,Product> products;

    public ProductRepositoryImpl(Map<String, Product> products) {
        this.products = new HashMap<>();
        this.products.putAll(products);
    }

    @Override
    public Collection<Product> findAll() {
        return null;
    }

    @Override
    public Product add(Product product) {
        return null;
    }
}
