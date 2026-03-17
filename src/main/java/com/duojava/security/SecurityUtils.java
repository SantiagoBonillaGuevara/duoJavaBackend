package com.duojava.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.UUID;

public final class SecurityUtils {

    private SecurityUtils() {}

    // Obtiene el UUID del usuario autenticado (sub del JWT de Supabase)
    public static UUID requireCurrentUserId() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof JwtAuthenticationToken jwtAuth) {
            return UUID.fromString(jwtAuth.getToken().getSubject());
        }
        throw new IllegalStateException("No hay usuario autenticado en el contexto");
    }

    // Obtiene el email del usuario autenticado
    public static String requireCurrentUserEmail() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof JwtAuthenticationToken jwtAuth) {
            return jwtAuth.getToken().getClaimAsString("email");
        }
        throw new IllegalStateException("No hay usuario autenticado en el contexto");
    }

    // Obtiene el rol del usuario (authenticated, service_role, etc.)
    public static String requireCurrentUserRole() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof JwtAuthenticationToken jwtAuth) {
            return jwtAuth.getToken().getClaimAsString("role");
        }
        throw new IllegalStateException("No hay usuario autenticado en el contexto");
    }
}