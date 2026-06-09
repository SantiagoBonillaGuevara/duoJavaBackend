package com.duojava.repository;

import com.duojava.domain.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, UUID> {

    List<Lesson> findByCourseIdOrderByOrderIndexAsc(UUID courseId);

    @Query("SELECT l FROM Lesson l LEFT JOIN FETCH l.exercises e WHERE l.id = :id ORDER BY e.orderIndex ASC")
    Optional<Lesson> findByIdWithExercises(UUID id);
}