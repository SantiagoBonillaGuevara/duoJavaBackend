package com.duojava.controller;

import com.duojava.dto.course.CurrentCourseResponse;
import com.duojava.security.SecurityUtils;
import com.duojava.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-jwt")
public class CourseController {

    private final CourseService courseService;

    @Operation(summary = "Obtener el curso actual del usuario autenticado")
    @GetMapping("/current")
    public ResponseEntity<CurrentCourseResponse> getCurrentCourse() {
        return ResponseEntity.ok(
                courseService.getCurrentCourseForUser(SecurityUtils.requireCurrentUserId())
        );
    }
}
