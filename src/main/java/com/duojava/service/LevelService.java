package com.duojava.service;

import com.duojava.dto.level.LevelResponse;
import com.duojava.exception.ResourceNotFoundException;
import com.duojava.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LevelService {

    private final LevelRepository levelRepository;

    public List<LevelResponse> getAllLevels() {
        return levelRepository.findAll()
                .stream()
                .sorted((a, b) -> a.getLevelNumber().compareTo(b.getLevelNumber()))
                .map(this::toResponse)
                .toList();
    }

    public LevelResponse getLevelByNumber(Integer levelNumber) {
        return levelRepository.findByLevelNumber(levelNumber)
                .map(this::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Level", levelNumber));
    }

    private LevelResponse toResponse(com.duojava.domain.entity.Level l) {
        return new LevelResponse(
                l.getId(),
                l.getLevelNumber(),
                l.getTitle(),
                l.getDescription(),
                l.getXpRequired(),
                l.getBadgeIcon(),
                l.getBadgeColor(),
                l.getDifficulty()
        );
    }
}
