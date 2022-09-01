package com.cs.mall;

import com.cs.mall.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger.web.SecurityConfiguration;

@SpringBootApplication
@EnableOpenApi
public class MallApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MallApplication.class, args);
//        String[] beans = run.getBeanDefinitionNames();
//
//        for(String bean:beans){
//
//            System.out.println(bean);
//
//        }
    }


}
