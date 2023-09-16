package com.red.bo.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.swagger.v3.oas.models.security.SecurityScheme.In.QUERY;
import static io.swagger.v3.oas.models.security.SecurityScheme.Type.APIKEY;
import static io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP;
import static io.swagger.v3.oas.models.security.SecurityScheme.Type.OAUTH2;
import static java.util.Arrays.asList;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI contactApi() {
        return new OpenAPI()
                .security(
                        asList(new SecurityRequirement().addList(APIKEY.name()), new SecurityRequirement().addList(OAUTH2.name())))
                .components(new Components()
                        .addSecuritySchemes(OAUTH2.name(), new SecurityScheme()
                                .type(HTTP)
                                .in(QUERY)
                                .bearerFormat("JWT")
                                .scheme("bearer")
                                .flows(new OAuthFlows()
                                        .authorizationCode(new OAuthFlow()
                                                .authorizationUrl( "/login")
                                                .tokenUrl("/token")))))
                .info(new Info().title("Contacts Api")
                        .version("1.0")
                        .description("The REST API for contacts ressources.")
                        .contact(new Contact()
                                .email("red.bo1402@gmail.com")
                                .url("")))
                .addServersItem(new Server()
                        .description("Local server")
                        .url("http://localhost:8080"));
    }
}
