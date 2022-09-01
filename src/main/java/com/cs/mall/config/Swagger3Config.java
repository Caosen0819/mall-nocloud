//package com.cs.mall.config;
//
//import com.cs.mall.common.config.Swaggerconfig;
//import com.cs.mall.common.properties.SwaggerProperties;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
//import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
//import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
//import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
//import org.springframework.boot.actuate.endpoint.web.*;
//import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
//import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
//import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.util.StringUtils;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.oas.annotations.EnableOpenApi;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger.annotations.Annotations;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
///**
// * @Author Caosen
// * @Date 2022/8/7 15:55
// * @Version 1.0
// * swagger3配置
// */
//@Configuration
//
//public class Swagger3Config {
//
////    @Override
////    public SwaggerProperties swaggerProperties() {
////        return SwaggerProperties.builder()
////                .apiBasePackage("com.cs.mall.controller")
////                .title("mall后台系统")
////                .description("mall后台相关接口文档")
////                .contactName("macro")
////                .version("1.0")
////                .enableSecurity(true)
////                .build();
////    }
////
////    @Bean
////    public BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
////        return generateBeanPostProcessor();
////    }
//    /**
//     * ture 启用Swagger3.0， false 禁用（生产环境要禁用）
//     */
//    Boolean swaggerEnabled=true;
//    @Bean
//    public Docket createRestApi(){
//        return new Docket(DocumentationType.OAS_30)
//                .apiInfo(apiInfo())
//                // 是否开启
//                .enable(swaggerEnabled)
//                .select()
//                // 扫描的路径使用@Api的controller
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                // 指定路径处理PathSelectors.any()代表所有的路径
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo(){
//        return new ApiInfoBuilder()
//                .title("Swagger3接口文档")
//                .description("接口测试")
//                //作者信息
//                .contact(new Contact("曹森","www.baidu.com", "caosen0819@163.com"))
//                .version("1.0")
//                .build();
//    }
//    @Bean
//    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier, ServletEndpointsSupplier servletEndpointsSupplier, ControllerEndpointsSupplier controllerEndpointsSupplier, EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsProperties, WebEndpointProperties webEndpointProperties, Environment environment) {
//        List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
//        Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
//        allEndpoints.addAll(webEndpoints);
//        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
//        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
//        String basePath = webEndpointProperties.getBasePath();
//        EndpointMapping endpointMapping = new EndpointMapping(basePath);
//        boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
//        return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes, corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath), shouldRegisterLinksMapping, null);
//    }
//    private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment, String basePath) {
//        return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath) || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
//    }
//
//}
