package com.example.maven.springbootlearn.master_slave_db_demo.dbconf;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import lombok.var;

@Component
@PropertySource("classpath:master-slave-db/dataSource.properties")
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@ConditionalOnProperty("doubledb.enable")
public class DbSource {
    
    @Bean("masterDataSourceProperties")
    @ConfigurationProperties("spring.datasource.master")
    HikariConfig dataSourceMasterProperties() {
        return new HikariConfig();
    }  

    @Bean("masterDataSource")
    public DataSource master( @Qualifier("masterDataSourceProperties") HikariConfig config) {
        return new HikariDataSource(config);
    }
    
    @Bean("slaveDataSourceProperties")
    @ConfigurationProperties("spring.datasource.slave")
    HikariConfig dataSourceSlaveProperties() {
        return new HikariConfig();
    }

    @Bean("slaveDataSource")
    @ConfigurationProperties("spring.datasource-slave")
    public DataSource slaveDataSource( @Qualifier("slaveDataSourceProperties") HikariConfig config) {
        return new HikariDataSource(config);
    }

    @Primary
    @Bean
    DataSource dataSource(
            @Autowired @Qualifier("masterDataSource") DataSource masterDataSource,
            @Autowired @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        var ds = new RoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("master", masterDataSource);
        dataSourceMap.put("slave", slaveDataSource);
        // 关联两个DataSource:
        ds.setTargetDataSources(dataSourceMap);
        // 默认使用masterDataSource:
        ds.setDefaultTargetDataSource(masterDataSource);
        return ds;
    }
    @Bean
    DataSourceTransactionManager dataSourceTransactionManager(@Autowired DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}

