package com.encora.management.service;

import com.encora.commons.dto.Product;

import java.util.Collection;
import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product addProduct(Product product);
}
