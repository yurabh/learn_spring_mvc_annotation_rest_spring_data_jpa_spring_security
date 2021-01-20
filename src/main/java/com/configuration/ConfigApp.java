package com.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"com.controller", "com.service", "com.dao"})
@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.repository")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ConfigApp {

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory, DataSource dataSource) {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(factory);
        manager.setDataSource(dataSource);
        return manager;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/learn_spring_mvc_annotation?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean managerFactory = new LocalContainerEntityManagerFactoryBean();
        managerFactory.setDataSource(dataSource);
        managerFactory.setJpaVendorAdapter(jpaVendorAdapter);
        managerFactory.setPackagesToScan("com.domain");
        return managerFactory;
    }

    @Bean
    public JpaVendorAdapter adapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
        adapter.setGenerateDdl(true);
        return adapter;
    }
}
