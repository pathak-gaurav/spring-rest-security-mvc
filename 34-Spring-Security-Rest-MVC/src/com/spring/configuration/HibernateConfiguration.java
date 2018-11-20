package com.spring.configuration;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.spring")
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class HibernateConfiguration {

	@Autowired
	private Environment env;

	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource source = new DriverManagerDataSource();
		source.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
		source.setUrl(env.getRequiredProperty("jdbc.url"));
		source.setUsername(env.getRequiredProperty("jdbc.username"));
		source.setPassword(env.getRequiredProperty("jdbc.password"));
		return source;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean session = new LocalSessionFactoryBean();
		session.setDataSource(dataSource());
		session.setPackagesToScan(new String[] { "com.spring" });
		session.setHibernateProperties(hibernateProperties());
		return session;
	}

	private Properties hibernateProperties() {
		final Properties props = new Properties();
		props.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		props.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		props.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		return props;
	}
	
	@Autowired
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory session){
		HibernateTransactionManager tx =  new HibernateTransactionManager();
		tx.setSessionFactory(session);
		return tx;
	}

}
