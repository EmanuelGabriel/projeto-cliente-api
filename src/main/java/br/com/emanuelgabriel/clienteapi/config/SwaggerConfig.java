package br.com.emanuelgabriel.clienteapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docAPI(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.emanuelgabriel.clienteapi.resource.v1"))
                .paths(PathSelectors.ant("/api/v1/**"))
                .build()
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Controle de Clientes - API")
                .description("Gerencia as informações de clientes e seu controle de acesso")
                .version("1.0.0")
                .license("SGSTech Ltda - Soluções Tecnológicas em Software.")
                .contact(new Contact("Samuel Gabriel Sousa", "https://www.sgstech.com.br", "emanuel.gabriel.sousa@protonmail.com"))
                .build();
    }

}
