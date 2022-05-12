package com.encora.microservice.repository.impl;

import com.encora.commons.constants.ProceduresNames;
import com.encora.commons.dto.Product;
import com.encora.microservice.repository.ProductDAO;
import com.encora.microservice.repository.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.util.*;

public class ProductDAOImpl implements ProductDAO {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductDAOImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ProductMapper productMapper;


    public ProductDAOImpl() {
    }

    @Override
    public Collection<Product> findAll() {

        List<Product> productList = new ArrayList<>();
        try{
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName(ProceduresNames.LIST_PRODUCTS)
                    .returningResultSet("listProducts",productMapper);

            SqlParameterSource in = new MapSqlParameterSource(null);

            Map<String, Object> simpleJdbcCallResult = jdbcCall.execute(in);

            productList.addAll((List<Product>) simpleJdbcCallResult.get(ProceduresNames.LIST_PRODUCTS_SET));
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }

        return productList;
    }

    @Override
    public Product add(Product product) {
        try{
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName(ProceduresNames.ADD_PRODUCT);
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue(ProceduresNames.NAME_PARAM,product.getName());
            parameters.addValue(ProceduresNames.TYPE_PARAM,product.getType());
            parameters.addValue(ProceduresNames.DESCRIPTION_PARAM,product.getDescription());
            SqlParameterSource in = parameters;

            Map<String, Object> result = jdbcCall.execute(in);

            Integer id = (Integer) result.get(ProceduresNames.ID_PARAM);

            if(id != null){
                product.setId(id);
            }else{
                product.setId(0);
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            product.setId(0);
        }

        return product;
    }

}
