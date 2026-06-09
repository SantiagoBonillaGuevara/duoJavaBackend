package com.duojava.repository;

import com.duojava.domain.entity.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgress, UUID> {

    // Todas las lecciones completadas por el usuario
    List<UserProgress> findByUserIdAndCompletedTrue(UUID userId);

    // IDs de lecciones completadas de un curso específico
    @Query("""
        SELECT up FROM UserProgress up
        JOIN up.lesson l
        WHERE up.userId = :userId
        AND l.course.id = :courseId
        AND up.completed = true
    """)
    List<UserProgress> findCompletedLessonsByCourse(UUID userId, UUID courseId);

    // Verifica si ya completó una lección específica
    boolean existsByUserIdAndLessonIdAndCompletedTrue(UUID userId, UUID lessonId);
}