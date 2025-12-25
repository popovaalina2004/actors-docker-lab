package com.actors.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UpdateActorRequest(
        @NotBlank(message = "Имя не может быть пустым")
        @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ .-]+$", message = "Имя может содержать только буквы, точки, пробелы и дефис")
        String name,
        @Min(value = 0, message = "Год рождения не может быть отрицательным")
        int birth_year
) {
}
