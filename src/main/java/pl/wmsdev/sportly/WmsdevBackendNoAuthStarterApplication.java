package pl.wmsdev.sportly;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Sportly API Documentation",
				version = "1.0",
				description = "API documentation for Sportly application"
		),
		servers = {
				@Server(url = "https://hackyeah2024-backend.wmsdev.pl", description = "WMS_DEV Backend Server URL"),
				@Server(url = "http://localhost:9914", description = "Localhost instance")
		}
)
@EnableAsync
public class WmsdevBackendNoAuthStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(WmsdevBackendNoAuthStarterApplication.class, args);
	}

}
