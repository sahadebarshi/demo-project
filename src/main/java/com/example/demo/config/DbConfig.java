package com.example.demo.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@Slf4j
public class DbConfig {


    @Primary
    @Bean
    public HibernateTemplate hibernateTemplate() throws SQLException {
        log.info("HIBERNATE TEMPLATE.......");
        return new HibernateTemplate(sessionFactory());
    }


    @Autowired
    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory)
    {
        log.info("CONFIGURING TRANSACTION MANAGER...");
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager(sessionFactory);
        hibernateTransactionManager.setDefaultTimeout(30);
        return hibernateTransactionManager;
    }
    @Bean
    public SessionFactory sessionFactory() throws SQLException {
        LocalSessionFactoryBean lsbf = new LocalSessionFactoryBean();
        lsbf.setDataSource(dataSource());
        lsbf.setPackagesToScan("com.example.demo");
        lsbf.setHibernateProperties(hibernateProperties());
        try{
            lsbf.afterPropertiesSet();
        } catch (IOException e) {
            log.info("SESSION FACTORY ERROR IS ----> ", e);
            throw new RuntimeException(e);
        }
        return lsbf.getObject();
    }


    public MysqlDataSource dataSource() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/MyTest");
        dataSource.setDatabaseName("MyTest");
        dataSource.setUser("root");
        dataSource.setPassword("baku");
        log.info("DATABASE CONNECTION STATUS.................. Connected");
        return dataSource;
    }

    public Properties hibernateProperties(){
        log.info("Hibernate Properties...");
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.default_schema", "MyTest");

        return properties;
    }


}
