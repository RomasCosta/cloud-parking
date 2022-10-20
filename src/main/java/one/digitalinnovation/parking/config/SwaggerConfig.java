///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package one.digitalinnovation.parking.config;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.service.AuthorizationScope;
//import springfox.documentation.service.BasicAuth;
//import springfox.documentation.service.SecurityReference;
//import springfox.documentation.service.SecurityScheme;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
///**
// *
// * @author romar
//*/
//@Component
//@EnableSwagger2
//public class SwaggerConfig {
//
//        @Bean
//        public Docket api() {
//                return new Docket(DocumentationType.SWAGGER_2)
//                        .select()
//                        .apis(RequestHandlerSelectors.basePackage("one.digitalinnovation.parking"))            
//                        .build()
//                        .apiInfo(metaData())
//                        .securityContexts(Arrays.asList(getSecurityContext()))
//                        .securitySchemes(Arrays.asList(basicAuthScheme()));
//        }
//        
//        private SecurityScheme basicAuthScheme() {
//            return new BasicAuth("basicAuth");
//        }
//         
//        private SecurityContext getSecurityContext() {
//            return SecurityContext.builder()
//                    .securityReferences(Arrays.asList(basicAuthReference()))
//                    .build();
//        }
//        
//        private SecurityReference basicAuthReference() {
//            return new SecurityReference("basicAuth", new AuthorizationScope[0]);
//        }
//        
//        private List<SecurityScheme> basicScheme() {
//        List<SecurityScheme> schemeList = new ArrayList<>();
//        schemeList.add(new BasicAuth("basicAuth"));
//        return schemeList;
//        }
//
//        private ApiKey apiKey() {
//            return new ApiKey("apiKey", "Authorization", "header");
//        }
//
//        private ApiInfo metaData() {//passar informacoes da api como titulo, descricao etc..
//               return new ApiInfoBuilder()
//                       .title("Parking REST API")
//                       .description("Spring Boot REST API for Parking")
//                       .version("1.0.0")
//                       .license("Apache Lincense Version 2.0")
//                       .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
//                       .build();
//        }
//}
// 