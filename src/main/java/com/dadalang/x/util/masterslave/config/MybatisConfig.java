package com.dadalang.x.util.masterslave.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @version 1.0
 * @Author lj
 * @date 2021/7/8 11:04 上午
 * @desc
 */
@EnableTransactionManagement
@Configuration
public class MybatisConfig {
    @Bean(name = "SqlSessionFactory")
    @Primary
    public SqlSessionFactory MasterSqlSessionFactory(DataSource routingDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(routingDataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try{
            // xml文件位置 如果用注解，则不需要这个
//            bean.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));
//            bean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
            return bean.getObject();
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Bean(name = "TransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(DataSource routingDataSource) {
        return new DataSourceTransactionManager(routingDataSource);
    }

    @Bean(name = "SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate MasterSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws  Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
