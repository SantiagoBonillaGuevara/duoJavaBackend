package com.duojava.controller;

import com.duojava.dto.profile.ProfileResponse;
import com.duojava.dto.profile.UpdateProfileRequest;
import com.duojava.security.SecurityUtils;
import com.duojava.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Profile", description = "Perfil del usuario autenticado")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-jwt")
public class ProfileController {

    private final ProfileService profileService;

    @Operation(summary = "Obtener mi perfil")
    @GetMapping("/me")
    public ResponseEntity<ProfileResponse> getMyProfile() {
        return ResponseEntity.ok(
                profileService.getMyProfile(SecurityUtils.requireCurrentUserId())
        );
    }

    @Operation(summary = "Actualizar mi perfil")
    @PatchMapping("/me")
    public ResponseEntity<ProfileResponse> updateMyProfile(
            @Valid @RequestBody UpdateProfileRequest request
    ) {
        return ResponseEntity.ok(
                profileService.updateMyProfile(SecurityUtils.requireCurrentUserId(), request)
        );
    }

    @Operation(summary = "Verificar disponibilidad de username")
    @GetMapping("/username-available")
    public ResponseEntity<Map<String, Boolean>> checkUsername(@RequestParam String username) {
        boolean available = profileService.isUsernameAvailable(username);
        return ResponseEntity.ok(Map.of("available", available));
    }
}