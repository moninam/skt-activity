package com.encora.commons.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = MessageList.class)
public class MessageList implements Serializable {
    private String tag;
    private List<Product> productList;

    public MessageList(){
        this.tag = UUID.randomUUID().toString();
        productList = new ArrayList<>();
    }
    public MessageList(List<Product> list){
        this.tag = UUID.randomUUID().toString();
        productList = new ArrayList<>();
        this.productList.addAll(list);
    }

    public void setList(List<Product> products){
        this.productList.addAll(products);
    }
    public List<Product> getProductList(){
        return new ArrayList<>(productList);
    }
    public String getTag(){
        return this.tag;
    }

    @Override
    public String toString() {
        return "MessageList{" +
                "tag='" + tag + '\'' +
                ", productList=" + productList +
                '}';
    }
}
