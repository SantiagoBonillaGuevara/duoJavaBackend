package com.duojava.repository;

import com.duojava.domain.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {

    List<Course> findAllByIsPublishedTrueOrderByOrderIndexAsc();

    Optional<Course> findByIdAndIsPublishedTrue(UUID id);

    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.lessons l WHERE c.id = :id ORDER BY l.orderIndex ASC")
    Optional<Course> findByIdWithLessons(UUID id);
}
