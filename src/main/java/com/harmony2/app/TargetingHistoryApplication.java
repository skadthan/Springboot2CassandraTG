package com.harmony2.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Suresh Kadthan
 * @version 1.0
 * @since Feb 02, 2018
 */

@SpringBootApplication
@ComponentScan("com.harmony2.app") 
@EntityScan("com.harmony2.app")
@EnableCassandraRepositories("com.harmony2.app")
@EnableReactiveCassandraRepositories("com.harmony2.app")
@EnableAsync
@EnableTransactionManagement
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class}) //, ConfigurationPropertiesAutoConfiguration.class, ProjectInfoAutoConfiguration.class,ReactiveSecurityAutoConfiguration.class,EmbeddedWebServerFactoryCustomizerAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class
public class TargetingHistoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TargetingHistoryApplication.class, args);
	}
}
