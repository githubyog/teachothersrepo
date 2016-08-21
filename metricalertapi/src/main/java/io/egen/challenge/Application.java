package io.egen.challenge;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

import org.easyrules.spring.RulesEngineFactoryBean;

import com.mongodb.MongoClient;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean(name="mongodbDataStore")
    public Datastore getMongoDBDataStore(@Value("${mongodb.serverHost}") String host,
										 @Value("${mongodb.serverPort}") Integer port,
										 @Value("${mongodb.schema}") String schema
										 ){
    	final Morphia morphia = new Morphia();
    	morphia.mapPackage("io.egen.challenge.vo");
    	final Datastore datastore = morphia.createDatastore(new MongoClient(host, port), schema);
    	datastore.ensureIndexes();
    	return datastore;
    }
    
    @Bean(name="rulesEngine")
    @Scope("prototype")
    public RulesEngineFactoryBean getRulesEngineFactoryBean(){
    	final RulesEngineFactoryBean rulesEngineFactoryBean = new RulesEngineFactoryBean();
    	rulesEngineFactoryBean.setSkipOnFirstAppliedRule(false);
    	rulesEngineFactoryBean.setSkipOnFirstFailedRule(false);
    	return rulesEngineFactoryBean;
    }
    
    @Bean
    public static  PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
    	return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    public LocaleResolver localeResolver(){
    	SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
    	sessionLocaleResolver.setDefaultLocale(Locale.US);
    	return sessionLocaleResolver;
    }
    
    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
    	ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    	messageSource.setBasename("classpath:messages");
    	return messageSource;
    }
}