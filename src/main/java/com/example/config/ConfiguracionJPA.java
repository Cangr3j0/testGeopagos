package com.example.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@EnableJpaRepositories("com.example.repositories") 
@Configuration
@Profile("oracle")
public class ConfiguracionJPA {

	@Bean
	 public DataSource dataSource() {
	 DriverManagerDataSource dataSource = new DriverManagerDataSource();
	 dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	 dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
	 dataSource.setUsername("lucho");
	 dataSource.setPassword("36871326");
	 return dataSource;
	 }
	 @Bean
	 public JpaTransactionManager
	 transactionManager(EntityManagerFactory emf) {
	 return new JpaTransactionManager(emf);
	 }
	 @Bean
	 public JpaVendorAdapter jpaVendorAdapter() {
	 HibernateJpaVendorAdapter jpaVendorAdapter = new
	 HibernateJpaVendorAdapter(); 
	 jpaVendorAdapter.setDatabase(Database.ORACLE); 
	 jpaVendorAdapter.setShowSql(true); 
	 return jpaVendorAdapter;
	 }
	 @Bean
	 public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	 LocalContainerEntityManagerFactoryBean
	 localContainerEntityManagerFactoryBean = 
	 new LocalContainerEntityManagerFactoryBean();
	 localContainerEntityManagerFactoryBean.setDataSource(dataSource()); 
	 Properties properties = new Properties(); 
	 properties.put("hibernate.hbm2ddl.auto", "create"); 
	 localContainerEntityManagerFactoryBean. 
	 setJpaProperties(properties); 
	 localContainerEntityManagerFactoryBean. 
	 setJpaVendorAdapter(jpaVendorAdapter()); 
	 localContainerEntityManagerFactoryBean. 
	 setPackagesToScan("com.example.entidades","com.example.repositorios");
	 return localContainerEntityManagerFactoryBean;
	 } 
//	 @Bean
//	 public Docket api() {                
//	     return new Docket(DocumentationType.SWAGGER_2)          
//	       .select()                                       
//	       .apis(RequestHandlerSelectors.basePackage("com.example.testegeopagos.PlayerController"))
//	       .paths(PathSelectors.ant("/foos/*"))                     
//	       .build();
//	 }
}
