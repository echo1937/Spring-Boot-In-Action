package org.example.druid.multi.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"org.example.druid.multi.two.mapper"}, sqlSessionFactoryRef = "secondarySqlSessionFactory")
public class SecondaryDataSourceConfig {

    @Bean
    public DataSource secondaryDatasource() {
        return new SecondDruidDataSourceWrapper();
    }

    @Bean
    public SqlSessionFactory secondarySqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(secondaryDatasource());
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate secondarySqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(secondarySqlSessionFactory());
    }

}
