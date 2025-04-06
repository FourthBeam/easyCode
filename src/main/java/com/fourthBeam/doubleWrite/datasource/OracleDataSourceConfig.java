package com.fourthBeam.doubleWrite.datasource;

import com.fourthBeam.doubleWrite.MybatisDoubleInteceptor;
import com.zaxxer.hikari.HikariDataSource;
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
@MapperScan(basePackages = OracleDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "oracleSqlSessionFactory")
public class OracleDataSourceConfig {
    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.fourthBeam.mapper.oracle";
    static final String MAPPER_LOCATION = "classpath:/mapper/oracle/*.xml";

    @Value("${spring.datasource.oracle.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.oracle.url}")
    private String url;
    @Value("${spring.datasource.oracle.username}")
    private String username;
    @Value("${spring.datasource.oracle.password}")
    private String password;

    @Bean("oracleDataSource")
    public DataSource createDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "oracleSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("oracleDataSource") DataSource oracleDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(oracleDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(OracleDataSourceConfig.MAPPER_LOCATION));
        sessionFactory.setPlugins(new MybatisDoubleInteceptor());
        return sessionFactory.getObject();
    }
}
