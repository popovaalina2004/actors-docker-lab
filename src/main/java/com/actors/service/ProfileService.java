package com.actors.service;

import com.actors.dto.ProfileDTO;
import com.actors.exception.DataNotFoundException;
import com.actors.handler.ProfileValidationException;
import com.actors.mapper.ProfileMapper;
import com.actors.repository.ProfileRepository;
import com.actors.request.CreateProfileRequest;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    public ProfileService(ProfileRepository profileRepository, ProfileMapper profileMapper) {
       this.profileRepository = profileRepository;
       this.profileMapper = profileMapper;
    }

    public ProfileDTO findByLogin(String login) {
        return profileRepository.findByLogin(login)
                .map(profileMapper::toProfileDTO)
                .orElseThrow(() -> new DataNotFoundException("Пользователь не найден"));
    }

    public ProfileDTO create(CreateProfileRequest createProfileRequest) {
        if (profileRepository.findByLogin(createProfileRequest.getUsername()).isPresent()){
            throw new ProfileValidationException("Пользователь уже существует");
        }
        profileRepository.create(createProfileRequest);
        return profileMapper.toProfileDTO(
                profileRepository.findByLogin(createProfileRequest.getUsername()).get()
        );
    }
}
