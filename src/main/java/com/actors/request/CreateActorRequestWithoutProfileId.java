package com.actors.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateActorRequestWithoutProfileId(
        @NotBlank(message = "Имя не может быть пустым")
        String name,
        @Min(value = 0, message = "Год рождения не может быть отрицательным")
        int birth_year
) {
}
