package org.example.druid.single;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(value = "org.example.druid.single.*.mapper")
public class SingleDruid {

    public static void main(String[] args) {
        SpringApplication.run(SingleDruid.class, args);
    }

}
