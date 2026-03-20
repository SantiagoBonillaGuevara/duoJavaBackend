package com.duojava.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "lessons")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "order_index", nullable = false)
    private Integer orderIndex;

    @Column(name = "xp_reward", nullable = false)
    private Integer xpReward;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "lesson", fetch = FetchType.LAZY)
    @OrderBy("orderIndex ASC")
    private List<Exercise> exercises;
}