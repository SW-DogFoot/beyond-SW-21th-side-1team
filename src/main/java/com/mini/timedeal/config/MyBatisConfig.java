package com.mini.timedeal.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

public class MyBatisConfig {

    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            sqlSessionFactory = buildSqlSessionFactory();
        }
        return sqlSessionFactory;
    }

    private static SqlSessionFactory buildSqlSessionFactory() {
        try {
            DataSource dataSource = createDataSource();

            Environment environment = new Environment(
                "development",
                new JdbcTransactionFactory(),
                dataSource
            );

            Configuration configuration = new Configuration(environment);

            configuration.setMapUnderscoreToCamelCase(true);

            String resource = "mappers/PromotionMapper.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            configuration.addMapper(com.mini.timedeal.domain.promotion.mapper.PromotionMapper.class);

            org.apache.ibatis.builder.xml.XMLMapperBuilder mapperParser = 
                new org.apache.ibatis.builder.xml.XMLMapperBuilder(
                    inputStream, 
                    configuration, 
                    resource, 
                    configuration.getSqlFragments()
                );
            mapperParser.parse();
            
            return new SqlSessionFactoryBuilder().build(configuration);
            
        } catch (IOException e) {
            throw new RuntimeException("MyBatis Mapper 파일을 로드할 수 없습니다.", e);
        }
    }

    private static DataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/timedealdb");
        config.setUsername("timedeal");
        config.setPassword("timedeal");
        config.setDriverClassName("org.mariadb.jdbc.Driver");

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        
        return new HikariDataSource(config);
    }
}
