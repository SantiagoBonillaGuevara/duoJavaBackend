package com.duojava.dto.profile;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

public record ProfileResponse(
        UUID id,
        String username,
        String displayName,
        String avatarUrl,
        Integer xp,
        Integer levelNumber,
        Integer streak,
        LocalDate lastActivityDate,
        OffsetDateTime createdAt
) {}