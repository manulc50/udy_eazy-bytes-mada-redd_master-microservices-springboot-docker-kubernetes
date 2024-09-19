package com.mlorenzo.customerservice;

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
				title = "Customer microservice REST API Documentation",
				description = "Customer microservice REST API Documentation",
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
				description = "Account microservice REST API Documentation",
				url = "https://www.test.com"
		)
)
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

}
