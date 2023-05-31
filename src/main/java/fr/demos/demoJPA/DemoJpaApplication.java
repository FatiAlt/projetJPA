package fr.demos.demoJPA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(enableDefaultTransactions = false)
public class DemoJpaApplication {

	// lancement de Spring
	public static void main(String[] args) {
		SpringApplication.run(DemoJpaApplication.class, args);
	}

}
