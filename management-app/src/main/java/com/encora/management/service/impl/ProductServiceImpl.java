package com.encora.management.service.impl;

import com.encora.commons.dto.Product;
import com.encora.management.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
//TODO: Implement operations to load and store message to queue
@Service
public class ProductServiceImpl implements ProductService {


    public ProductServiceImpl() {
    }

    @Override
    public Collection<Product> getProducts() {
        return new ArrayList<>();
    }

    @Override
    public Product addProduct(Product product) {
        //TODO: Implement logic to send to the queue
        return null;
    }
}
