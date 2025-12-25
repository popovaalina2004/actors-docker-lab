package com.actors.mapper;

import com.actors.dto.ProfileDTO;
import com.actors.entity.Profile;
import org.springframework.stereotype.Component;

@Component
public class ProfileMapper {

    public ProfileDTO toProfileDTO(Profile profile) {
       return new ProfileDTO(
               profile.getId(),
               profile.getLogin()
       );
    }
}
