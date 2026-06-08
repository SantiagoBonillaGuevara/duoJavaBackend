package com.duojava.controller;

import com.duojava.dto.level.LevelResponse;
import com.duojava.service.LevelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Levels", description = "Niveles y progreso XP")
@RestController
@RequestMapping("/levels")
@RequiredArgsConstructor
public class LevelController {

    private final LevelService levelService;

    @Operation(summary = "Listar todos los niveles")
    @GetMapping
    public ResponseEntity<List<LevelResponse>> getAllLevels() {
        return ResponseEntity.ok(levelService.getAllLevels());
    }

    @Operation(summary = "Obtener nivel por número")
    @GetMapping("/{levelNumber}")
    public ResponseEntity<LevelResponse> getLevelByNumber(@PathVariable Integer levelNumber) {
        return ResponseEntity.ok(levelService.getLevelByNumber(levelNumber));
    }
}
