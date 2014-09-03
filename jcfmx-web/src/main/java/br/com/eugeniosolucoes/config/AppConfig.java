/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugeniosolucoes.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author eugenio
 */
@EnableWebMvc
@Configuration
@EnableTransactionManagement
@ComponentScan( basePackages = "br.com.eugeniosolucoes" )
public class AppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers( ResourceHandlerRegistry registry ) {
        registry.addResourceHandler( "/resources/**" ).addResourceLocations( "/resources/" );
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix( "/WEB-INF/views/" );
        resolver.setSuffix( ".jsp" );

        return resolver;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean factoryBean
                = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource( dataSource() );
        factoryBean.setPackagesToScan( new String[]{ "br.com.eugeniosolucoes.model" } );

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql( true );
        //vendorAdapter.setGenerateDdl(generateDdl)

        factoryBean.setJpaVendorAdapter( vendorAdapter );

        Properties additionalProperties = new Properties();
        additionalProperties.put( "hibernate.hbm2ddl.auto", "update" );

        factoryBean.setJpaProperties( additionalProperties );

        return factoryBean;
    }

    @Bean
    public DataSource dataSource() {
        final ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try {
            dataSource.setDriverClass( "org.postgresql.Driver" );
        } catch ( PropertyVetoException e ) {
            throw new RuntimeException( e );
        }

        dataSource.setJdbcUrl( "jdbc:postgresql://localhost:5432/jcfmx" );
        dataSource.setUser( "postgres" );
        dataSource.setPassword( "postgres" );
        dataSource.setMinPoolSize( 3 );
        dataSource.setMaxPoolSize( 15 );
        dataSource.setDebugUnreturnedConnectionStackTraces( true );

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory( entityManagerFactoryBean().getObject() );

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean( name = "messageSource" )
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
        bean.setBasename( "classpath:messages" );
        bean.setDefaultEncoding( "UTF-8" );
        return bean;
    }

    @Bean( name = "validator" )
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource( messageSource() );
        return bean;
    }

    @Override
    public Validator getValidator() {
        return validator();
    }

}
