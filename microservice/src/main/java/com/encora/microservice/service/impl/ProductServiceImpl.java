package com.encora.microservice.service.impl;

import com.encora.commons.dto.Product;
import com.encora.microservice.repository.ProductRepository;
import com.encora.microservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    public ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {

        return new ArrayList<>(productRepository.findAll());
    }

    @Override
    public Product saveProduct(Product product) {
        productRepository.add(product);
        return product;
    }
}
