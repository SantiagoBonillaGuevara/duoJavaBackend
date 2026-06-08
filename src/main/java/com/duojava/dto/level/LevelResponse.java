package com.duojava.dto.level;

import java.util.UUID;

public record LevelResponse(
        UUID id,
        Integer levelNumber,
        String title,
        String description,
        Integer xpRequired,
        String badgeIcon,
        String badgeColor,
        Integer difficulty
) {}