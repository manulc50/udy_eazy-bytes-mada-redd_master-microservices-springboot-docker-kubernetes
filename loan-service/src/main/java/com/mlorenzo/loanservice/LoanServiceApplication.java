package com.mlorenzo.loanservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
		info = @Info(
				title = "Loan microservice REST API Documentation",
				description = "Loan microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "John Doe",
						email = "john.doe@test.com",
						url = "https://www.test.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.test.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Loan microservice REST API Documentation",
				url = "https://www.test.com"
		)
)
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@SpringBootApplication
public class LoanServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanServiceApplication.class, args);
	}

}
