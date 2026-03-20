package com.duojava.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "exercise_submissions")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(name = "submitted_code", nullable = false)
    private String submittedCode;

    private String output;

    @Column(nullable = false)
    private Boolean passed;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "test_results", columnDefinition = "jsonb")
    private String testResults;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "memory_snapshots", columnDefinition = "jsonb")
    private String memorySnapshots;

    @Column(name = "xp_earned", nullable = false)
    private Integer xpEarned;

    @Column(name = "execution_time_ms")
    private Integer executionTimeMs;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;
}