package com.dadalang.x.util.masterslave.config;

import com.dadalang.x.util.masterslave.MyAbstractRoutingDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/7/5 3:17 下午
 * @desc
 */
@Configuration
public class DataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource MasterDataSource() {
        System.out.println(" ------ datasource master initial");
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(@Qualifier("MasterDataSource") DataSource dataSource) {
        System.out.println(" ------ datasource master before initial");
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("/db/mysql/schema.sql"));
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(populator);
        return initializer;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource SlaveDataSource() {
        System.out.println(" ------ datasource slave initial");
        return DataSourceBuilder.create().build();
    }

    @Bean
    public AbstractRoutingDataSource routingDataSource() {
        Map<Object, Object> map = new HashMap<>(2);   // 存放数据源的映射
        map.put("master", MasterDataSource());
        map.put("slave", SlaveDataSource());

        MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource();
        proxy.setDefaultTargetDataSource(MasterDataSource());
        proxy.setTargetDataSources(map);
        return proxy;
    }
}
