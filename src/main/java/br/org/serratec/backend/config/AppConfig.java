package br.org.serratec.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer{

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		System.out.println("BCRYPT");
		return new BCryptPasswordEncoder();
	}
	
	
	/* @Override
    public void addCorsMappings(CorsRegistry registry) {
		System.out.println("CORS");
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:3000/"
                		)
                .allowedMethods("*")
                .allowCredentials(true);
    }
*/
}

