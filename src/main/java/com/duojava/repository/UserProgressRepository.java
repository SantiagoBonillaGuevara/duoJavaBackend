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

    // Total de lecciones completadas por el usuario
    long countByUserIdAndCompletedTrue(UUID userId);

    // Total de cursos completados (todas sus lecciones completadas)
    @Query("""
    SELECT COUNT(DISTINCT l.course.id)
    FROM UserProgress up
    JOIN up.lesson l
    WHERE up.userId = :userId
    AND up.completed = true
    AND (
        SELECT COUNT(l2.id)
        FROM Lesson l2
        WHERE l2.course.id = l.course.id
    ) = (
        SELECT COUNT(up2.id)
        FROM UserProgress up2
        JOIN up2.lesson l3
        WHERE up2.userId = :userId
        AND up2.completed = true
        AND l3.course.id = l.course.id
    )
""")
    long countCompletedCoursesByUser(UUID userId);
}