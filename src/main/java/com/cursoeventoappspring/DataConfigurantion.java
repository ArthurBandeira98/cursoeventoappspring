package com.cursoeventoappspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DataConfigurantion {

//	@Bean
//	public BasicDataSource dataSource() throws URIReferenceException, URISyntaxException {
//
//		URI dbUri = new URI(System.getenv("DATABASE_URL"));
//
//		String username = dbUri.getUserInfo().split(":")[0];
//		String password = dbUri.getUserInfo().split(":")[1];
//		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath()
//				+ "?sslmode=require";
//
//		BasicDataSource basicDataSource = new BasicDataSource();
//		basicDataSource.setUrl(dbUrl);
//		basicDataSource.setUsername(username);
//		basicDataSource.setPassword(password);
//
//		return basicDataSource;
//
//	}

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

	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(
				"jdbc:mysql://localhost:3306/eventospring?useTimezone=true&serverTimezone=UTC&createDatabaseIfNotExist=true");
		dataSource.setUsername("root");
		dataSource.setPassword("admin");
		return dataSource;

	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
		adapter.setPrepareConnection(true);
		return adapter;

	}

}
