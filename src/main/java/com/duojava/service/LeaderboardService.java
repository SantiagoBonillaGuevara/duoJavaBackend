package com.duojava.service;

import com.duojava.dto.leaderboard.LeaderboardEntryResponse;
import com.duojava.domain.entity.Level;
import com.duojava.repository.ProfileRepository;
import com.duojava.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LeaderboardService {

    private final ProfileRepository profileRepository;
    private final LevelRepository levelRepository;

    public List<LeaderboardEntryResponse> getLeaderboard() {
        var profiles = profileRepository.findAllOrderByXpDesc();

        // Cargamos todos los niveles en un Map para no hacer N queries
        Map<Integer, Level> levelsMap = levelRepository.findAll()
                .stream()
                .collect(Collectors.toMap(Level::getLevelNumber, l -> l));

        List<LeaderboardEntryResponse> result = new ArrayList<>();
        for (int i = 0; i < profiles.size(); i++) {
            var p = profiles.get(i);
            Level level = levelsMap.get(p.getLevelNumber());

            result.add(new LeaderboardEntryResponse(
                    i + 1,
                    p.getId(),
                    p.getUsername(),
                    p.getDisplayName(),
                    p.getAvatarUrl(),
                    p.getXp(),
                    p.getLevelNumber(),
                    level != null ? level.getTitle() : "Novato",
                    level != null ? level.getBadgeIcon() : "🌱",
                    level != null ? level.getBadgeColor() : "#6B9E6B"
            ));
        }

        return result;
    }
}