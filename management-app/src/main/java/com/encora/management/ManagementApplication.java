package com.encora.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.encora.management")
public class ManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagementApplication.class, args);
    }
}
