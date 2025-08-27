package com.pe				"  🚀 Pet Paradise Backend Started Successfully! 🚀\n" +
				"  Server running on: http://localhost:9090/api\n" +
				"  Swagger UI: http://localhost:9090/swagger-ui.html\n" +
				"  H2 Database Console: http://localhost:9090/h2-console\n" +adise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PetParadiseBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetParadiseBackendApplication.class, args);
		System.out.println("\n" +
				"==============================================\n" +
				"  🐾 Pet Paradise Backend Started Successfully! 🐾\n" +
				"  Server running on: http://localhost:8080/api/v1\n" +
				"  Swagger UI: http://localhost:8080/api/v1/swagger-ui.html\n" +
				"  H2 Database Console: http://localhost:8080/api/v1/h2-console\n" +
				"==============================================\n");
	}
}
