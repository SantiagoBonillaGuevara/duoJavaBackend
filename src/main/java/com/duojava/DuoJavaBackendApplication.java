package com.duojava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class DuoJavaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DuoJavaBackendApplication.class, args);
    }

}
