package com.example.property;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(value = {PersonProperties.class})
@SpringBootApplication
public class SpringBootPropertiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPropertiesApplication.class, args);
    }

}
