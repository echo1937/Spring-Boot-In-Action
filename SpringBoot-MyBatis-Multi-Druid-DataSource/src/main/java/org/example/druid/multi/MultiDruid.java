package org.example.druid.multi;

//import org.example.druid.multi.config.SecondDruidDataSourceWrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
//@EnableConfigurationProperties({SecondDruidDataSourceWrapper.class})
public class MultiDruid {

    public static void main(String[] args) {
        SpringApplication.run(MultiDruid.class, args);
    }

}
