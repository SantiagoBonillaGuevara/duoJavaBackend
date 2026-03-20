package com.duojava.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_achievements")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAchievement {

    @EmbeddedId
    private UserAchievementId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("achievementId")
    @JoinColumn(name = "achievement_id")
    private Achievement achievement;

    @Column(name = "earned_at")
    private OffsetDateTime earnedAt;

    @Embeddable
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class UserAchievementId implements Serializable {
        @Column(name = "user_id")
        private UUID userId;

        @Column(name = "achievement_id")
        private UUID achievementId;
    }
}