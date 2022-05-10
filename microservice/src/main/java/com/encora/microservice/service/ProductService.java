package com.encora.microservice.service;

import com.encora.commons.dto.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getProducts();
    public Product saveProduct(Product product);
}
