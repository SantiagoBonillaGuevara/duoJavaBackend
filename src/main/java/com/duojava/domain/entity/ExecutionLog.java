package com.duojava.domain.entity;

import com.duojava.domain.enums.ExecutionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "execution_logs")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExecutionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submission_id")
    private ExerciseSubmission submission;

    @Column(name = "user_id")
    private UUID userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExecutionStatus status;

    private String stdout;
    private String stderr;

    @Column(name = "execution_time_ms")
    private Integer executionTimeMs;

    @Column(name = "memory_used_kb")
    private Integer memoryUsedKb;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;
}