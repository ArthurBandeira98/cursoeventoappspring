package com.cursoeventoappspring;

import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.crypto.URIReferenceException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfigurantion {

	@Bean
	public BasicDataSource dataSource() throws URIReferenceException, URISyntaxException {

		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath()
				+ "?sslmode=require";

		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUrl(dbUrl);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);

		return basicDataSource;

	}

	// criando tabelas auto
	// @Bean
	// public JpaVendorAdapter jpaVendorAdapter() {
	// HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
	// adapter.setDatabase(Database.POSTGRESQL);
	// adapter.setShowSql(true);
	// adapter.setGenerateDdl(true);
	// adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
	// adapter.setPrepareConnection(true);
	// return adapter;
	// }

	// CONFIGURAÇÃO MYSQL

	// ?useTimezone=true&serverTimezone=UTC

	// @Bean
	// public DataSource dataSource() {
	// DriverManagerDataSource dataSource = new DriverManagerDataSource();
	// dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	// dataSource.setUrl("jdbc:mysql://localhost:3306/eventospring?useTimezone=true&serverTimezone=UTC");
	// dataSource.setUsername("root");
	// dataSource.setPassword("admin");
	// return dataSource;
	//
	// }
	//
	// @Bean
	// public JpaVendorAdapter jpaVendorAdapter() {
	// HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
	// adapter.setDatabase(Database.MYSQL);
	// adapter.setShowSql(true);
	// adapter.setGenerateDdl(true);
	// adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
	// adapter.setPrepareConnection(true);
	// return adapter;
	//
	// }

}
