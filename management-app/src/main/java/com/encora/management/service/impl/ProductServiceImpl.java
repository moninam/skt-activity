package com.encora.management.service.impl;

import com.encora.commons.dto.Product;
import com.encora.management.service.ProductService;
import com.encora.microservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
//TODO: Implement operations to load and store message to queue
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Collection<Product> getProducts() {
        return this.productRepository.findAll()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Product addProduct(Product product) {
        final Product savedProduct = productRepository.add(product);
        return savedProduct;
    }
}
