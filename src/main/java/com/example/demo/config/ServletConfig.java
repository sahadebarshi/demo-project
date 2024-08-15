package com.example.demo.config;


import com.example.demo.servlet.CustomServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean interceptorServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(
                new CustomServlet(), "/home/ping");
        bean.setLoadOnStartup(2);
        return bean;
    }
}
