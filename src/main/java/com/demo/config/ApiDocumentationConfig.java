package com.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiDocumentationConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .addServersItem(new Server().url("http://localhost:8080"))
                .info(new Info()
                        .version("1.0")
                        .title("BadWords Service")
                        .description("Spring Boot Java Microservice for Checking Bad Words in a Sentence")
                        .contact(new Contact().name("Sachin").email("shouryaraj24@gmail.com")));


    }
}
