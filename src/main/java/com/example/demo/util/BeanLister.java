package com.example.demo.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BeanLister implements CommandLineRunner {
    private final ApplicationContext applicationContext;

    @Autowired
    public BeanLister(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        int count = 1;
        for (String beanName : beanNames) {
            if("myTransactionManager".equalsIgnoreCase(beanName) || "transactionManager".equalsIgnoreCase(beanName))
             log.info(count+" > "+"REGISTERED BEAN NAME : {}",beanName);
            count++;
        }
    }
}
