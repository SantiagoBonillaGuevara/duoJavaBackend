package com.duojava.service;

import com.duojava.domain.entity.Profile;
import com.duojava.dto.profile.ProfileResponse;
import com.duojava.dto.profile.UpdateProfileRequest;
import com.duojava.exception.BusinessException;
import com.duojava.exception.ResourceNotFoundException;
import com.duojava.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {

    private final ProfileRepository profileRepository;

    // Obtener perfil del usuario autenticado
    public ProfileResponse getMyProfile(UUID userId) {
        Profile profile = profileRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", userId));
        return toResponse(profile);
    }

    // Crear perfil justo después del registro en Supabase
    @Transactional
    public ProfileResponse createProfile(UUID userId) {
        if (profileRepository.existsById(userId)) {
            return toResponse(profileRepository.findById(userId).get());
        }

        Profile profile = Profile.builder()
                .id(userId)
                .xp(0)
                .levelNumber(1)
                .streak(0)
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build();

        return toResponse(profileRepository.save(profile));
    }

    // Actualizar username, displayName o avatarUrl
    @Transactional
    public ProfileResponse updateMyProfile(UUID userId, UpdateProfileRequest request) {
        Profile profile = profileRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", userId));

        if (request.username() != null) {
            if (!isUsernameAvailable(request.username())) {
                throw new BusinessException("El username '" + request.username() + "' ya está en uso");
            }
            profile.setUsername(request.username());
        }

        if (request.displayName() != null) {
            profile.setDisplayName(request.displayName());
        }

        if (request.avatarUrl() != null) {
            profile.setAvatarUrl(request.avatarUrl());
        }

        profile.setUpdatedAt(OffsetDateTime.now());

        return toResponse(profileRepository.save(profile));
    }

    public Boolean isUsernameAvailable(String username) {
        return !profileRepository.existsByUsername(username);
    }

    // Mapper
    private ProfileResponse toResponse(Profile p) {
        return new ProfileResponse(
                p.getId(),
                p.getUsername(),
                p.getDisplayName(),
                p.getAvatarUrl(),
                p.getXp(),
                p.getLevelNumber(),
                p.getStreak(),
                p.getLastActivityDate(),
                p.getCreatedAt()
        );
    }
}