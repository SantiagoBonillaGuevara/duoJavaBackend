package com.duojava.domain.entity;

import com.duojava.domain.enums.ExerciseType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "exercises")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "exercise_type")
    private ExerciseType type;

    @Column(name = "starter_code")
    private String starterCode;

    @Column(name = "solution_code")
    private String solutionCode;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "test_cases", columnDefinition = "jsonb")
    private String testCases;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "visualization_steps", columnDefinition = "jsonb")
    private String visualizationSteps;

    @Column(name = "order_index", nullable = false)
    private Integer orderIndex;

    @Column(name = "xp_reward", nullable = false)
    private Integer xpReward;

    @Column(nullable = false)
    private Integer difficulty;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;
}