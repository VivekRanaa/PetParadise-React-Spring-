package com.petparadise.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI petParadiseOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:9090/api");
        devServer.setDescription("Development Server");

        Contact contact = new Contact();
        contact.setEmail("admin@petparadise.com");
        contact.setName("Pet Paradise Team");
        contact.setUrl("https://petparadise.com");

        License license = new License()
                .name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Pet Paradise API")
                .version("1.0.0")
                .contact(contact)
                .description("REST API for Pet Paradise - A comprehensive pet care service platform")
                .termsOfService("https://petparadise.com/terms")
                .license(license);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
    }
}
