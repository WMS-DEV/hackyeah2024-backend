package pl.wmsdev.sportly;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "UniSearch",
				description = "HackYeah 2023 project by WMS_DEV",
				version = "development"
		)
)
@EnableAsync
public class WmsdevBackendNoAuthStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(WmsdevBackendNoAuthStarterApplication.class, args);
	}

}
