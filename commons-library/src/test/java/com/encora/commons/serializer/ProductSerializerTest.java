package com.encora.commons.serializer;


import com.encora.commons.dto.Product;
import com.encora.commons.enums.ProductType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductSerializerTest {

    Product product;

    List<Product> productList = new ArrayList<>();
    ProductSerializer productSerializer;
    String json;

    String wrongJson;

    String listJson;
    @Before
    public void setUp(){
        productSerializer = new ProductSerializer();
        product = new Product(1,"Sabritas", ProductType.FOOD,"Sabritas");
        json ="{" +
                "\"id\":"+"1" + ","+
                "\"name\":"+"\"Sabritas\"" + ","+
                "\"type\":"+"\"FOOD\"" + ","+
                "\"description\":"+"\"Sabritas\"" +
                "}";
        wrongJson = "";
        listJson = "[" +
                "{" +
                "\"id\":1," +
                "\"name\":\"Samsung\"," +
                "\"type\":\"CELL_PHONE\"," +
                "\"description\":\"Samsung\"" +
                "}," +
                "{" +
                "\"id\":2," +
                "\"name\":\"Chips\"," +
                "\"type\":\"FOOD\"," +
                "\"description\":\"Chips\"" +
                "}]";
        productList.add(new Product(1,"Samsung",ProductType.CELL_PHONE,"Samsung"));
        productList.add( new Product(2,"Chips",ProductType.FOOD,"Chips"));

    }
    @Test
    public void testDeserializeObject() {
        Product productDeserialized = productSerializer.deserializeObject(json);
        assertThat(productDeserialized).isEqualToComparingFieldByField(product);
    }
    @Test()
    public void testDeserializeObjectWithError(){
        Product productDeserialized = productSerializer.deserializeObject(wrongJson);
        assertThat(productDeserialized).isNull();
    }
    @Test
    public void testSerializeObject(){
        String response = productSerializer.serializeObject(product);
        assertThat(response).isEqualTo(json);
    }
    @Test
    public void testSerializeObjectWithError(){
        String response = productSerializer.serializeObject(null);
        assertThat(response).isNullOrEmpty();
    }
    @Test
    public void testSerializeList(){
        String response = productSerializer.serializeList(productList);
        assertThat(response).isEqualTo(listJson);
    }
    @Test
    public void testDeserializeList(){
        List<Product> response = productSerializer.deserializeList(listJson);
        String responseString = response.toString();
        String productString = productList.toString();
        assertThat(responseString).isEqualTo(productString);
    }
    @Test
    public void testDeserializeListWithError(){
        List<Product> response = productSerializer.deserializeList(wrongJson);
        assertThat(response).isNull();
    }

}
