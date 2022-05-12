package com.encora.commons.serializer;

import com.encora.commons.dto.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ProductSerializer {
    private final static Logger LOGGER = LoggerFactory.getLogger(ProductSerializer.class);

    public Product deserializeObject(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;
        try {
            product = mapper.readValue(json, Product.class);
            return product;
        } catch (IOException e) {
            LOGGER.error(e.getStackTrace().toString());
        }
        return product;
    }
    public String serializeObject(Product product) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(product);
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getStackTrace().toString());
        }
        return null;
    }

    public List<Product> deserializeList(String json) {
        ObjectMapper mapper = new ObjectMapper();
        List<Product> productList = null;
        try {
            productList = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, Product.class));
            return productList;
        } catch (IOException e) {
            LOGGER.error(e.getStackTrace().toString());
        }
        return productList;
    }

    public String serializeList(List<Product> products) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(products);
        } catch (JsonProcessingException e) {
            LOGGER.error(e.getStackTrace().toString());
        }
        return null;
    }
}
