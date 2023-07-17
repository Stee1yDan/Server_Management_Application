package com.example.servermanagementapp;

import com.example.servermanagementapp.enumeration.Status;
import com.example.servermanagementapp.model.Server;
import com.example.servermanagementapp.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class ServerManagementAppApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ServerManagementAppApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ServerRepository serverRepo)
    {
        return args -> {
            serverRepo.save(new Server(null, "192.168.1.160", "Ubuntu Linux", "16 GB", "Personal PC", "http://localhost:8080/server/image/1.png", Status.SERVER_RUNNING));
            serverRepo.save(new Server(null, "192.168.1.58", "Fedora Linux", "16 GB", "Dell Tower","http://localhost:8080/server/image/2.png", Status.SERVER_RUNNING));
            serverRepo.save(new Server(null, "192.168.1.21", "MS 2008", "32 GB", "Web Server", "http://localhost:8080/server/image/3.png", Status.SERVER_DOWN));
            serverRepo.save(new Server(null, "192.168.1.14", "Red Hat Enterprise Linux", "64 GB", "Mail Server", "http://localhost:8080/server/image/4.png", Status.SERVER_RUNNING));
        };
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
