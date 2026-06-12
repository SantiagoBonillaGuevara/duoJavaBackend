package com.duojava.dto.course;

import java.util.UUID;

public record CourseWithProgressResponse(
        UUID id,
        String title,
        String description,
        Integer orderIndex,
        Integer totalLessons,
        Integer completedLessons,
        Integer progressPercent,
        Integer xpPotential,       // suma de xpReward de todas las lecciones
        Integer xpEarned,          // xp ganado en este curso
        CourseStatus status        // COMPLETED | IN_PROGRESS | LOCKED
) {
    public enum CourseStatus {
        COMPLETED, IN_PROGRESS, LOCKED
    }
}