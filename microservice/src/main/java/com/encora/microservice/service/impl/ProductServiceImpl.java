package com.encora.microservice.service.impl;

import com.encora.commons.dto.Product;
import com.encora.microservice.repository.ProductDAO;
import com.encora.microservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    public ProductDAO productDAO;

    @Override
    public List<Product> getProducts() {

        return new ArrayList<>(productDAO.findAll());
    }

    @Override
    public Product saveProduct(Product product) {
        Product productResponse = productDAO.add(product);
        return productResponse;
    }
    public void setProductDAO(ProductDAO productDAO){
        this.productDAO = productDAO;
    }
}
