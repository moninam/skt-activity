package com.encora.microservice.repository.mapper;

import com.encora.commons.dto.Product;

import com.encora.commons.enums.ProductType;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("product_id"));
        product.setName(rs.getString("product_name"));
        product.setType(ProductType.valueOf(rs.getString("product_type")));
        product.setDescription(rs.getString("product_description"));
        return product;
    }
}