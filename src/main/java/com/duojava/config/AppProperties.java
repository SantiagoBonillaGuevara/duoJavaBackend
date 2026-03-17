package com.duojava.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "app")
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