package org.example.hikari.multi.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"org.example.hikari.multi.one.mapper"}, sqlSessionFactoryRef = "primarySqlSessionFactory")
public class PrimaryDataSourceConfig {

    @Primary
    @Bean
    public DataSource primaryDatasource() {
        return new PrimaryHikariDataSource();
    }

    @Primary
    @Bean
    public SqlSessionFactory primarySqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(primaryDatasource());
        return sqlSessionFactoryBean.getObject();
    }

    @Primary
    @Bean
    public SqlSessionTemplate primarySqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(primarySqlSessionFactory());
    }

}
