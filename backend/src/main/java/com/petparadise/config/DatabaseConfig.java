package com.petparadise.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import java.net.URI;

@Configuration
@Profile("prod")
public class DatabaseConfig {

    @Value("${DATABASE_URL}")
    private String databaseUrl;

    @Bean
    public DataSource getDataSource() {
        try {
            // Parse the DATABASE_URL
            URI dbUri = URI.create(databaseUrl);
            
            String host = dbUri.getHost();
            int port = dbUri.getPort() != -1 ? dbUri.getPort() : 5432;
            String database = dbUri.getPath().substring(1); // Remove leading slash
            String[] userInfo = dbUri.getUserInfo().split(":");
            String username = userInfo[0];
            String password = userInfo[1];
            
            String jdbcUrl = String.format("jdbc:postgresql://%s:%d/%s", host, port, database);
            
            System.out.println("=== DATABASE CONFIG ===");
            System.out.println("Original URL: " + databaseUrl);
            System.out.println("JDBC URL: " + jdbcUrl);
            System.out.println("Username: " + username);
            System.out.println("Host: " + host);
            System.out.println("Port: " + port);
            System.out.println("Database: " + database);
            System.out.println("=======================");
            
            return DataSourceBuilder.create()
                    .driverClassName("org.postgresql.Driver")
                    .url(jdbcUrl)
                    .username(username)
                    .password(password)
                    .build();
                    
        } catch (Exception e) {
            System.err.println("Failed to parse DATABASE_URL: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Unable to configure database", e);
        }
    }
}
