package org.example.hikari.multi.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("spring.datasource.hikari")
public class PrimaryHikariDataSource extends HikariDataSource {
}
