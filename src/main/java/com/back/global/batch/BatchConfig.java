package com.back.global.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.EnableJdbcJobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@EnableJdbcJobRepository
public class BatchConfig {

    @Bean
    @Profile("!prod") // NOTE: 운영환경이 아닐 때만 실행
    public DataSourceInitializer notProdDataSourceInitializer(DataSource dataSource) { // NOTE: DataSourceInitializer는 SQL 실행 관리자
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator(); // NOTE: ResourceDatabasePopulator는 SQL을 준비하는 역할
        populator.addScript(new ClassPathResource("/org/springframework/batch/core/schema-h2.sql"));
        populator.setContinueOnError(true); // NOTE: 오류가 발생해도 계속 진행한다. (이미 테이블이 생성된 경우 오류없이 넘어가기 위해 필요한 코드다.)

        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(populator);
        return initializer;
    }
}