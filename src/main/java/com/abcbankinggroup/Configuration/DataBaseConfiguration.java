package com.abcbankinggroup.Configuration;


import com.zaxxer.hikari.HikariConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "params.datasource")
public class DataBaseConfiguration extends HikariConfig {

//    @Bean
//    public DataSource dataSource() throws SQLException {
//        return new HikariDataSource(this);
//    }

}