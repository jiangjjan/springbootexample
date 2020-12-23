package com.example.maven.springbootlearn.master_slave_db_demo.dbconf;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {

    private static ThreadLocal<String> datasourceContext = new ThreadLocal<>();

    @Override
    protected Object determineCurrentLookupKey() {
        // 从ThreadLocal中取出key:
        return getDataSourceRoutingKey();
    }
    
    @Override
    protected DataSource determineTargetDataSource() {
        DataSource ds = super.determineTargetDataSource();
        System.out.println(String.format("determin target datasource: %s}", ds));
        return ds;
    }

    public static Object getDataSourceRoutingKey() {
		return datasourceContext.get();
    }
    public static void switchDataSource(String datasource) {
        System.out.println(String.format("determin target datasource: %s}", datasource));
        datasourceContext.set(datasource);
    }

    public static void clear() {
        datasourceContext.remove();
    }
}
