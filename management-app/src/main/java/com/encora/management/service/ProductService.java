package com.encora.management.service;

import com.encora.commons.dto.Product;
import com.encora.management.exception.OperationErrorException;

import java.util.List;

public interface ProductService {
    List<Product> getProducts() throws OperationErrorException;
    Product addProduct(Product product) throws OperationErrorException;
}
