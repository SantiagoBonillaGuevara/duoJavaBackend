package com.duojava.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "courses")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "order_index", nullable = false)
    private Integer orderIndex;

    @Column(name = "is_published", nullable = false)
    private Boolean isPublished;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @OrderBy("orderIndex ASC")
    private List<Lesson> lessons;
}