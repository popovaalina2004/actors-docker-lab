package com.actors.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Schema(description = "Данные для создания профиля", requiredProperties = {"username", "password"})
public class CreateProfileRequest {
    @NotBlank(message = "Имя не может быть пустым")
    @Length(min = 6, message = "Имя пользователя должно быть не меньше 6 символов")
    String username;
    @NotBlank(message = "Пароль не может быть пустым")
    @Length(min = 6, message = "Пароль не должен быть меньше 6 символов")
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CreateProfileRequest(String password, String username) {
        this.password = password;
        this.username = username;
    }
}
