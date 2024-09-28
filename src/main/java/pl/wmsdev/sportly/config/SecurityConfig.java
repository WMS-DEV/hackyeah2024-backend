package pl.wmsdev.sportly.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {

		http.csrf(csrf -> csrf.disable())
			.cors(cors -> cors.configurationSource(corsConfigurationSource))
			.authorizeHttpRequests(request -> request.anyRequest().permitAll());

		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource(@Value("${client.url:http://localhost}") String frontendUrl) {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin(frontendUrl);
		configuration.addAllowedOrigin("http://localhost");
		configuration.addAllowedOrigin("https://hackyeah2024-backend.wmsdev.pl");
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE", "HEAD", "OPTIONS"));
		configuration.addAllowedHeader("*");
		configuration.setAllowCredentials(true);
		configuration.setMaxAge(3600L);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
