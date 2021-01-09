package org.example.hikari.multi;


import org.example.hikari.multi.config.PrimaryHikariDataSource;
import org.example.hikari.multi.config.SecondaryHikariDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties({PrimaryHikariDataSource.class, SecondaryHikariDataSource.class})
public class MultiHikari {

    public static void main(String[] args) {
        SpringApplication.run(MultiHikari.class, args);
    }

}
