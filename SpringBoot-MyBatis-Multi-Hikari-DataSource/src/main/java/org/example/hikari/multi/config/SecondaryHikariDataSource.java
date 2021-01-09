package org.example.hikari.multi.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.datasource.second-hikari")
public class SecondaryHikariDataSource extends HikariDataSource {
}
