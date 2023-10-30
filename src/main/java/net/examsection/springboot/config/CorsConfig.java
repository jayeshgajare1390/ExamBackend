package net.examsection.springboot.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // Allow requests from your Angular frontend URL
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedMethod("*"); // You can customize this to allow specific HTTP methods
        config.addAllowedHeader("*"); // You can customize this to allow specific headers
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}

