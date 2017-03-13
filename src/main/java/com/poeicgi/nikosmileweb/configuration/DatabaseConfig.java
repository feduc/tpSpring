package com.poeicgi.nikosmileweb.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//Class jouee en premier
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

	// l'environment est forcément lié à un projet spring en lien avec application.prop

	@Autowired
	private Environment env;
	//appelle également la fonction dataSource
	@Autowired
	private DataSource dataSource;

	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;

		//map variable et fonction dataSource
		// indique à srping qu'il doit fusionner ces élément autowired et bean
		@Bean
		public DataSource datasource(){

		//objet de spring enfant de datasource
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		//recupere dans application.properties la valeur de dbdriver et autres
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		return dataSource;
	}

	@Bean
	// lance la fonction datasource en appelant la variable dataSource
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean ();
		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setPackagesToScan(env.getProperty("entitymanager.packagesToScan"));

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

		Properties additionalProperties = new Properties();
		additionalProperties.put("hibernate.dialect",env.getProperty("hibernate.dialect"));
		additionalProperties.put("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
		additionalProperties.put("hibernate.hdm2ddl.auto",env.getProperty("hibernate.hdm2ddl.auto"));
		entityManagerFactory.setJpaProperties(additionalProperties);

		return entityManagerFactory;
	}

	@Bean
	public JpaTransactionManager transactionManager (){

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
		return transactionManager;

	}

	@Bean
	//lever des erreurs si il y a des erreurs
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
