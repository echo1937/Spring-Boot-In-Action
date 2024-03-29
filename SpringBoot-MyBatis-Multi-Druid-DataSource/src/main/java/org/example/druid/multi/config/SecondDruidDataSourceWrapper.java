//package org.example.druid.multi.config;
//
//import com.alibaba.druid.filter.Filter;
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//
//import java.util.List;
//
//
//@ConfigurationProperties("spring.datasource.second-druid")
//public class SecondDruidDataSourceWrapper extends DruidDataSource implements InitializingBean {
//    @Autowired
//    private DataSourceProperties basicProperties;
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        //if not found prefix 'spring.datasource.druid' jdbc properties ,'spring.datasource' prefix jdbc properties will be used.
//        if (super.getUsername() == null) {
//            super.setUsername(basicProperties.determineUsername());
//        }
//        if (super.getPassword() == null) {
//            super.setPassword(basicProperties.determinePassword());
//        }
//        if (super.getUrl() == null) {
//            super.setUrl(basicProperties.determineUrl());
//        }
//        if (super.getDriverClassName() == null) {
//            super.setDriverClassName(basicProperties.getDriverClassName());
//        }
//    }
//
//    @Autowired(required = false)
//    public void autoAddFilters(List<Filter> filters) {
//        super.filters.addAll(filters);
//    }
//
//    /**
//     * Ignore the 'maxEvictableIdleTimeMillis < minEvictableIdleTimeMillis' validate,
//     * it will be validated again in {@link DruidDataSource#init()}.
//     * <p>
//     * for fix issue #3084, #2763
//     *
//     * @since 1.1.14
//     */
//    @Override
//    public void setMaxEvictableIdleTimeMillis(long maxEvictableIdleTimeMillis) {
//        try {
//            super.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);
//        } catch (IllegalArgumentException ignore) {
//            super.maxEvictableIdleTimeMillis = maxEvictableIdleTimeMillis;
//        }
//    }
//
//}
