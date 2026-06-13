package com.duojava.dto.leaderboard;

import java.util.UUID;

public record LeaderboardEntryResponse(
        Integer rank,
        UUID userId,
        String username,
        String displayName,
        String avatarUrl,
        Integer xp,
        Integer levelNumber,
        String levelTitle,
        String badgeIcon,
        String badgeColor
) {}