package com.duojava.service;

import com.duojava.domain.entity.Course;
import com.duojava.domain.entity.Lesson;
import com.duojava.domain.entity.UserProgress;
import com.duojava.dto.course.CurrentCourseResponse;
import com.duojava.exception.ResourceNotFoundException;
import com.duojava.repository.CourseRepository;
import com.duojava.repository.LessonRepository;
import com.duojava.repository.UserProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseService {

    // Inyecta el nuevo repository
    private final UserProgressRepository userProgressRepository;
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;

    public CurrentCourseResponse getCurrentCourseForUser(UUID userId) {

        // Todos los cursos publicados ordenados
        List<Course> allCourses = courseRepository.findAllByIsPublishedTrueOrderByOrderIndexAsc();

        if (allCourses.isEmpty()) {
            throw new ResourceNotFoundException("No hay cursos disponibles");
        }

        Course currentCourse = null;

        for (Course course : allCourses) {
            List<Lesson> lessons = lessonRepository.findByCourseIdOrderByOrderIndexAsc(course.getId());
            int totalLessons = lessons.size();

            if (totalLessons == 0) continue;

            List<UserProgress> completedInCourse = userProgressRepository
                    .findCompletedLessonsByCourse(userId, course.getId());

            int completedLessons = completedInCourse.size();

            // Si no completó todas las lecciones → este es su curso actual
            if (completedLessons < totalLessons) {
                currentCourse = course;
                break;
            }
            // Si completó todas → sigue al siguiente curso
        }

        // Si completó todos los cursos → devuelve el último
        if (currentCourse == null) {
            currentCourse = allCourses.get(allCourses.size() - 1);
        }

        // Calcula progreso final
        List<Lesson> lessons = lessonRepository
                .findByCourseIdOrderByOrderIndexAsc(currentCourse.getId());
        int totalLessons = lessons.size();

        List<UserProgress> completedInCourse = userProgressRepository
                .findCompletedLessonsByCourse(userId, currentCourse.getId());
        int completedLessons = completedInCourse.size();

        boolean isCompleted = completedLessons >= totalLessons;
        int progressPercent = totalLessons > 0
                ? (int) Math.round((completedLessons * 100.0) / totalLessons)
                : 0;

        return new CurrentCourseResponse(
                currentCourse.getId(),
                currentCourse.getTitle(),
                currentCourse.getDescription(),
                currentCourse.getOrderIndex(),
                totalLessons,
                completedLessons,
                progressPercent,
                isCompleted
        );
    }
}

