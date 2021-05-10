package be.vdab.cultuurhuis;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CultuurhuisApplication {


public static void main(String[] args) {
	SpringApplication.run(CultuurhuisApplication.class, args);
}
@Bean
OpenAPI openAPI() {
	return new OpenAPI().info(
			new Info().title("Cultuurhuis").version("2.0.0") .description("Backend van Cultuurhuis")); }

}
