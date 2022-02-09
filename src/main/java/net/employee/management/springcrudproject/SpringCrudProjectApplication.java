package net.employee.management.springcrudproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition
public class SpringCrudProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCrudProjectApplication.class, args);
	}

	@Bean
	public OpenAPI customConfiguration() {
		return new OpenAPI()
				.components(new Components())
				.info(new Info().title("Employee Management ")
						.description("Api Rest documentation."));
	}

}
