package net.examsection.springboot.bean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class bean {
	    @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurer() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/save/**") // Adjust the mapping based on your endpoint URL pattern
	                        .allowedOrigins("http://localhost:4200") // Add the allowed origin(s)
	                        .allowedMethods("POST") // Add the allowed HTTP method(s)
	                        .allowedHeaders("*"); // Add the allowed header(s)
	            }
	        };
	    }
	}

