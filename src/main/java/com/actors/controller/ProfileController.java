package com.actors.controller;

import com.actors.dto.ProfileDTO;
import com.actors.request.CreateProfileRequest;
import com.actors.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@Tag(name = "Profile Controller", description = "Операции с профилями пользователей")
public class ProfileController {
    private final ProfileService profileService;
    public ProfileController(ProfileService profileService) {this.profileService = profileService;}

    @GetMapping("/by-username/{username}")
    @Operation(summary = "Поиск профиля")
    public ProfileDTO findByLogin(@PathVariable String username) {
        return profileService.findByLogin(username);
    }

    @Operation(summary = "Создать профиль")
    @PostMapping
    public ProfileDTO create(@Valid @RequestBody CreateProfileRequest createProfileRequest) {
        return profileService.create(createProfileRequest);
    }
}
