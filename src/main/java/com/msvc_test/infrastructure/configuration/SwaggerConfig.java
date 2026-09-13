package com.msvc_test.infrastructure.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(title = "API TEST", version = "1.0"),
        security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {

    // http://localhost:8080/swagger-ui/index.html
    @Bean
    public OpenAPI hotelCleaningMaterialsOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sistema (...)")
                        .description("API para (...)")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Soporte Técnico")
                                .url("https://github.com/Yalico23/SpringSecurity-HexagonalMultiRol-SpringBoot")
                                .email("soporte@soporte.com"))
                        .license(new License()
                                .name("Licencia Privada")
                                .url("https://github.com/Yalico23/SpringSecurity-HexagonalMultiRol-SpringBoot")));
    }
}
