package example.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CorsConfiguration
{
    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
                        .allowedHeaders(
                                "Host",
                                "User-Agent",
                                "X-Requested-With",
                                "Accept",
                                "Accept-Language",
                                "Accept-Encoding",
                                "Authorization",
                                "Referer",
                                "Connection",
                                "Content-Type")
                        .exposedHeaders("header1", "header2")
                        .maxAge(3600)
                        .allowCredentials(true);
            }
        };
    }
}
