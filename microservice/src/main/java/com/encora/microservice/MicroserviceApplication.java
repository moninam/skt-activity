package com.encora.microservice;

import com.encora.commons.serializer.ProductSerializer;
import com.encora.microservice.listener.QueueListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MicroserviceApplication {


    public static void main(String[] args) {
        SpringApplication.run(MicroserviceApplication.class, args);
    }

    @Bean
    public QueueListener listener() {
        return new QueueListener();

    }
    @Bean
    public ProductSerializer serializer() {
        return new ProductSerializer();
    }

}
