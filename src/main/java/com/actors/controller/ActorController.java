package com.actors.controller;



import com.actors.dto.ActorDTO;
import com.actors.exception.BadRequestException;
import com.actors.exception.DataNotFoundException;
import com.actors.request.CreateActorRequest;
import com.actors.request.CreateActorRequestWithoutProfileId;
import com.actors.request.UpdateActorRequest;
import com.actors.service.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actor")
@Tag(name = "Actor Controller", description = "Операции с актерами")
public class ActorController {
    private final ActorService actorService;

    public ActorController(ActorService actorService){
        this.actorService = actorService;
    }

    @Operation(summary = "Добавить актера")
    @PostMapping("/{profileId}/actors")
    public ActorDTO create(@PathVariable Long profileId, @Valid @RequestBody CreateActorRequestWithoutProfileId createActorRequest) {
        CreateActorRequest fullrequest = new CreateActorRequest(
                createActorRequest.name(),
                createActorRequest.birth_year(),
                profileId
        );
        return actorService.create(fullrequest);
    }

    @Operation(summary = "Удалить актера")
    @DeleteMapping("/{profileId}/actors/delete/{actorId}")
    public void delete(@PathVariable Long profileId, @PathVariable Long actorId) throws DataNotFoundException, BadRequestException {
        actorService.delete(profileId, actorId);
    }

    @Operation(summary = "Редактировать актера")
    @PutMapping("/{profileId}/actors/{actorId}/edit")
    public ActorDTO update(@PathVariable Long profileId, @PathVariable Long actorId, @Valid @RequestBody UpdateActorRequest updateActorRequest) {
        return actorService.update(profileId, actorId, updateActorRequest);
    }

    @Operation(summary = "Получить актера по id")
    @GetMapping("/{profileId}/actors/{actorId}")
    public ActorDTO getById(@PathVariable Long profileId, @PathVariable Long actorId) throws DataNotFoundException, BadRequestException {
        return actorService.getByIdForProfile(profileId, actorId);
    }

    @Operation(summary = "Поиск актера по имени")
    @GetMapping("/{profileId}/actors/search")
    public List<ActorDTO> findByName(@PathVariable Long profileId, @RequestParam String name) throws DataNotFoundException {
        return actorService.findByName(profileId, name);
    }

    @Operation(summary = "Получить всех актеров по логину")
    @GetMapping("/actors/{username}")
    public List<ActorDTO> getByProfileLogin(@RequestParam String username) throws DataNotFoundException {
        return actorService.findAll(username);
    }
}
