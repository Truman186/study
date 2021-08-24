package com.deyun.config;


//import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfigurationAdapter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2020/7/8.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:8080","null")
                        .allowedMethods("GET","POST","PUT","OPTIONS","DELETE")
                        .allowCredentials(true)
                        .maxAge(3600);
    }
}
