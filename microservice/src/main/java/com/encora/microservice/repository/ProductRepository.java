package com.encora.microservice.repository;

import com.encora.commons.dto.Product;

import java.util.Collection;

public interface ProductRepository {
    Collection<Product> findAll();
    Product add(Product product);
}
