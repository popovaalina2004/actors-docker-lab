package com.actors.service;

import com.actors.dto.ActorDTO;
import com.actors.entity.Actor;
import com.actors.entity.Profile;
import com.actors.exception.DataNotFoundException;
import com.actors.handler.ActorValidationException;
import com.actors.mapper.ProfileMapper;
import com.actors.repository.ActorRepository;
import com.actors.repository.ProfileRepository;
import com.actors.request.CreateActorRequest;
import com.actors.request.UpdateActorRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorService {

    private final ActorRepository actorRepository;
    private final ProfileRepository profileRepository;

    public ActorService(ActorRepository actorRepository, ProfileRepository profileRepository) {
        this.actorRepository = actorRepository;
        this.profileRepository = profileRepository;
    }

    public ActorDTO create(CreateActorRequest createActorRequest) {
        profileRepository.findById(createActorRequest.profileId())
                .orElseThrow(() -> new DataNotFoundException("Профиль не найден"));
        actorRepository.create(createActorRequest);
        Actor actor = actorRepository.findByName(createActorRequest.name()).get(0);
        return new ActorDTO(
                actor.getId_actor(),
                actor.getName(),
                actor.getBirth_year()
        );
    }

    public ActorDTO update(Long profileId, Long id, UpdateActorRequest request) {
        profileRepository.findById(profileId)
                .orElseThrow(() -> new DataNotFoundException("Профиль не найден"));
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Актер не найден"));
        if (!actor.getIdProfile().equals(profileId)) {
            throw new ActorValidationException("Актер не принадлежит указанному профилю");
        }
        actorRepository.update(id, request);
        return new ActorDTO(
                id,
                request.name(),
                request.birth_year()
        );
    }

    public void delete(Long profileId, Long id) {
        profileRepository.findById(profileId)
                .orElseThrow(() -> new DataNotFoundException("Профиль не найден"));
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Актер не найден"));
        if (!actor.getIdProfile().equals(profileId)) {
            throw new ActorValidationException("Актер не принадлежит указанному профилю");
        }
        actorRepository.delete(id);
    }

    public List<ActorDTO> findByName(Long profileId, String name) {
        return actorRepository.findByName(name).stream()
                .map(actor -> new ActorDTO(
                        actor.getId_actor(),
                        actor.getName(),
                        actor.getBirth_year()
                ))
                .collect(Collectors.toList());
    }

    public ActorDTO getByIdForProfile(Long profileId, Long id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Актер не найден"));
        if(!actor.getIdProfile().equals(profileId)) {
            throw new ActorValidationException("Актер не принадлежит указанному профилю");
        }
        return new ActorDTO(
                actor.getId_actor(),
                actor.getName(),
                actor.getBirth_year()
        );
    }

    public List<ActorDTO> findAll(String login) {
        Profile profile = profileRepository.findByLogin(login)
                .orElseThrow(() -> new DataNotFoundException("Профиль не найден"));
        return actorRepository.findAll(login).stream()
                .map(actor -> new ActorDTO(
                        actor.getId_actor(),
                        actor.getName(),
                        actor.getBirth_year()
                ))
                .collect(Collectors.toList());
    }
}
