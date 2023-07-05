package com.example.property;


import org.springframework.boot.context.properties.ConfigurationProperties;

// 2.6.4版本以前需要添加@ConstructorBinding
@ConfigurationProperties(prefix = "person")
public record PersonProperties(String name, String email) {
}
