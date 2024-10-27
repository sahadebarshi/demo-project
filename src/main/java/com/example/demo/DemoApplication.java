package com.example.demo;


import com.example.demo.route.ProductRouteBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;


//import javax.servlet.ServletContext;


//@EnableAutoConfiguration(exclude = {JpaRepositoriesAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@SpringBootApplication
@Slf4j
public class DemoApplication extends SpringBootServletInitializer  {

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}*/

	@Autowired
	CamelContext camelContext;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}

	@PostConstruct
	public void init() throws Exception {
		camelContext.addRoutes(new ProductRouteBuilder());
		camelContext.start();
		log.info("## CAMEL CONTEXT INITIATED....");
	}

	@PreDestroy
	public void stopCamelContext() throws Exception {
		camelContext.stop();
		log.info("## CAMEL CONTEXT STOPPED....");
	}

}
