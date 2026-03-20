package com.duojava.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "app") // Lee las propiedades de application.yml bajo "app"
public record AppProperties(
        SupabaseProperties supabase,
        CorsProperties cors
) {

    public record SupabaseProperties(
            String url
    ) {}

    public record CorsProperties(
            List<String> allowedOrigins
    ) {}
}