package com.example.helloSpringBoot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final CustomAuthenticationSuccessHandler successHandler;

	public SecurityConfig(CustomAuthenticationSuccessHandler successHandler) {
		this.successHandler = successHandler;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(
				auth -> auth.requestMatchers("/", "/login**", "/error**").permitAll().anyRequest().authenticated())
				.oauth2Login(oauth -> oauth.successHandler(successHandler) // özel success handler burada
				);

		return http.build();
	}

	// CORS ayarı (daha önce konuştuğumuz gibi)
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**").allowedOrigins("https://goalquiz.com").allowedMethods("GET", "POST")
						.allowCredentials(true);
			}
		};
	}
}
