package org.example.single;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(value = "org.example.single.*.mapper")
public class Single {

    public static void main(String[] args) {
        SpringApplication.run(Single.class, args);
    }

}
