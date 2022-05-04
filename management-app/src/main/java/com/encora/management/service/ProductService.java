package com.encora.management.service;

import com.encora.commons.dto.Product;

import java.util.Collection;

public interface ProductService {
    Collection<Product> getProducts();
    Product addProduct(Product product);
}
