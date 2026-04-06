package com.duojava.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "levels")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String title;

    private String description;

    private Integer difficulty;

    @Column(name = "level_number", unique = true)
    private Integer levelNumber;

    @Column(name = "xp_required", nullable = false)
    private Integer xpRequired;

    @Column(name = "badge_icon")
    private String badgeIcon;

    @Column(name = "badge_color")
    private String badgeColor;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}