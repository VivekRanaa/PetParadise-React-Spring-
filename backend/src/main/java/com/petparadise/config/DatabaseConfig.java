package com.petparadise.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

@Configuration
@Profile("prod")
public class DatabaseConfig {

    @Value("${DATABASE_URL}")
    private String databaseUrl;

    @Bean
    public DataSource getDataSource() {
        // Convert Render's DATABASE_URL format to proper JDBC URL
        String processedUrl = processDatabaseUrl(databaseUrl);
        
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url(processedUrl)
                .build();
    }

    private String processDatabaseUrl(String url) {
        // Handle different URL formats from cloud providers
        if (url.startsWith("postgres://")) {
            url = url.replace("postgres://", "jdbc:postgresql://");
        } else if (url.startsWith("postgresql://")) {
            url = url.replace("postgresql://", "jdbc:postgresql://");
        } else if (!url.startsWith("jdbc:postgresql://")) {
            url = "jdbc:postgresql://" + url;
        }
        
        // Add default port if missing
        if (!url.contains(":5432/") && !url.matches(".*:\\d+/.*")) {
            // Find the @ symbol and add :5432 after the host
            int atIndex = url.lastIndexOf("@");
            int slashIndex = url.indexOf("/", atIndex);
            if (atIndex != -1 && slashIndex != -1) {
                String beforeSlash = url.substring(0, slashIndex);
                String afterSlash = url.substring(slashIndex);
                url = beforeSlash + ":5432" + afterSlash;
            }
        }
        
        return url;
    }
}
