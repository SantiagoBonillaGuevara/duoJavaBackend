package com.duojava.service;

import com.duojava.domain.entity.Profile;
import com.duojava.dto.profile.ProfileResponse;
import com.duojava.dto.profile.UpdateProfileRequest;
import com.duojava.exception.BusinessException;
import com.duojava.exception.ResourceNotFoundException;
import com.duojava.repository.ProfileRepository;
import com.duojava.repository.UserProgressRepository;
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
    private final UserProgressRepository userProgressRepository;

    // Obtener perfil del usuario autenticado
    public ProfileResponse getMyProfile(UUID userId) {
        Profile profile = profileRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", userId));

        int completedLessons = (int) userProgressRepository
                .countByUserIdAndCompletedTrue(userId);

        int completedCourses = (int) userProgressRepository
                .countCompletedCoursesByUser(userId);

        return toResponse(profile, completedLessons, completedCourses);
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

        return toResponse(profileRepository.save(profile), 0, 0);
    }

    public Boolean isUsernameAvailable(String username) {
        return !profileRepository.existsByUsername(username);
    }

    // Mapper
    private ProfileResponse toResponse(Profile p,int completedLessons, int completedCourses) {
        return new ProfileResponse(
                p.getId(),
                p.getUsername(),
                p.getDisplayName(),
                p.getAvatarUrl(),
                p.getGoogleAvatarUrl(),
                p.getXp(),
                p.getLevelNumber(),
                p.getStreak(),
                p.getLastActivityDate(),
                p.getCreatedAt(),
                completedLessons,
                completedCourses
        );
    }
}