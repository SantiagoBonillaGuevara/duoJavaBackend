package com.duojava.dto.course;

import java.util.UUID;

public record CurrentCourseResponse(
        UUID id,
        String title,
        String description,
        Integer orderIndex,
        Integer totalLessons,
        Integer completedLessons,
        Integer progressPercent,
        boolean isCompleted       // true si ya terminó este curso
) {}
