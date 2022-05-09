package com.encora.microservice.configuration;

import com.encora.commons.dto.Product;
import com.encora.microservice.repository.ProductRepository;
import com.encora.microservice.repository.impl.ProductRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MicroserviceConfiguration {
    @Bean
    public ProductRepository provideProductRepository(){
        return new ProductRepositoryImpl(initData());
    }
    private static Map<String, Product> initData(){
        Map<String,Product> initData = new HashMap<>();
        initData.put("Product1",new Product(1,"Product 1","Food"));
        initData.put("Product2",new Product(2,"Product 2","Food"));
        initData.put("Product3",new Product(3,"Product 3","Food"));

        return initData;
    }
}
