package com.dz.tele2.config;

import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Alex on 26.09.15.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.dz.tele2.entity"})
@EnableJpaRepositories
@PropertySource("classpath:db.properties")
public class DataSourceConfig {

    /**
    This file used for configuration of database layer of application.
    It enables transaction management and Spring JPA Repositories.
    Also defines beans for ORM working.
    This class loaded by defining it in ServletInitializer class at "web" module.
     */

    private static final String PROP_DATABASE_DRIVER = "db.driver";
    private static final String PROP_DATABASE_PASSWORD = "db.password";
    private static final String PROP_DATABASE_URL = "db.url";
    private static final String PROP_DATABASE_USERNAME = "db.username";
    private static final String PROP_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROP_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROP_ENTITYMANAGER_PACKAGES_TO_SCAN = "db.entitymanager.packages.to.scan";
    private static final String PROP_HIBERNATE_FORMAT_SQL= "hibernate.format_sql";
    private static final String PROP_HIBERNATE_USE_SQL_COMMENTS = "hibernate.use_sql_comments";
    private static final String PROP_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";

    @Resource
    private Environment environment;

    /**
     * This method return Spring bean of DataSource class, which used for establish database connection
     *
     * @return DataSource class instance
     */
    @Bean
    public DataSource dataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty(PROP_DATABASE_DRIVER));
        dataSource.setUrl(environment.getRequiredProperty(PROP_DATABASE_URL));
        dataSource.setUsername(environment.getRequiredProperty(PROP_DATABASE_USERNAME));
        dataSource.setPassword(environment.getRequiredProperty(PROP_DATABASE_PASSWORD));
        return dataSource;
    }

    /**
     * This method returns Spring bean, that used to reproduce EntityManager class instances
     *
     * @param dataSource dataSource Spring bean that will be autowired
     * @return LocalContainerEntityManagerFactoryBean instance
     */
    @Bean
    @Autowired
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        return new LocalContainerEntityManagerFactoryBean() {
            {
                setDataSource(dataSource);
                setPersistenceProviderClass(HibernatePersistenceProvider.class);
                setJpaVendorAdapter(vendorAdapter);
                setPackagesToScan(environment.getRequiredProperty(PROP_ENTITYMANAGER_PACKAGES_TO_SCAN));
                setJpaProperties(getHibernateProperties());
            }
        };
    }

    /**
     * This method returns Spring bean of JpaTransactionManager class,
     * that used to manage transactions in JPA repositories
     *
     * @param entityManagerFactory entityManagerFactory Spring bean that will be autowired
     * @return instance of JpaTransactionManager class
     */
    @Bean
    @Autowired
    public JpaTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }

    private Properties getHibernateProperties() {
        return new Properties() {
            {
                setProperty(PROP_HIBERNATE_DIALECT, environment.getRequiredProperty(PROP_HIBERNATE_DIALECT));
                setProperty(PROP_HIBERNATE_SHOW_SQL, environment.getRequiredProperty(PROP_HIBERNATE_SHOW_SQL));
                setProperty(PROP_HIBERNATE_HBM2DDL_AUTO, environment.getRequiredProperty(PROP_HIBERNATE_HBM2DDL_AUTO));
                setProperty(PROP_HIBERNATE_FORMAT_SQL, environment.getRequiredProperty(PROP_HIBERNATE_FORMAT_SQL));
                setProperty(PROP_HIBERNATE_USE_SQL_COMMENTS,
                        environment.getRequiredProperty(PROP_HIBERNATE_USE_SQL_COMMENTS));
            }
        };
    }
}
