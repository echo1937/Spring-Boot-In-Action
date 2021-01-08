package org.example.multi;

import org.example.multi.config.SecondDruidDataSourceWrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties({SecondDruidDataSourceWrapper.class})
public class Multi {

    public static void main(String[] args) {
        SpringApplication.run(Multi.class, args);
    }

}
