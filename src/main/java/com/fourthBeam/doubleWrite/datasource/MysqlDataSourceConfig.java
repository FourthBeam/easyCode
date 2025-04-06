package com.fourthBeam.doubleWrite.datasource;

import com.fourthBeam.doubleWrite.MybatisDoubleInteceptor;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = MysqlDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "mysqlSqlSessionFactory")
public class MysqlDataSourceConfig {
    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.fourthBeam.mapper.mysql";
    static final String MAPPER_LOCATION = "classpath:/mapper/mysql/*.xml";

    @Value("${spring.datasource.mysql.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.mysql.url}")
    private String url;
    @Value("${spring.datasource.mysql.username}")
    private String username;
    @Value("${spring.datasource.mysql.password}")
    private String password;

    @Bean("mysqlDataSource")
    public DataSource createDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "mysqlSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("mysqlDataSource") DataSource mysqlDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mysqlDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MysqlDataSourceConfig.MAPPER_LOCATION));
        sessionFactory.setPlugins(new MybatisDoubleInteceptor());
        return sessionFactory.getObject();
    }
}
