package com.duojava.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "profiles")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {

    @Id
    private UUID id; // mismo UUID que auth.users de Supabase

    @Column(unique = true)
    private String username;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(nullable = false)
    private Integer xp;

    @Column(name = "level_number", nullable = false)
    private Integer levelNumber;

    @Column(nullable = false)
    private Integer streak;

    @Column(name = "last_activity_date")
    private LocalDate lastActivityDate;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "level_number", insertable = false, updatable = false)
    private Level level;
}