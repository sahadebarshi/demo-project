package com.example.demo.config;


import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamelConfig {

    @Bean
    CamelContext camelContext(){
        return new DefaultCamelContext();
    }
}
