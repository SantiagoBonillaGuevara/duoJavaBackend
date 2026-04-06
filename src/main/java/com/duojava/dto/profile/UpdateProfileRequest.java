package com.duojava.dto.profile;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateProfileRequest(

        @Size(min = 3, max = 30, message = "El username debe tener entre 3 y 30 caracteres")
        @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "El username solo puede contener letras, números y guiones bajos")
        String username,

        @Size(max = 50, message = "El display name no puede superar 50 caracteres")
        String displayName,

        String avatarUrl
) {}