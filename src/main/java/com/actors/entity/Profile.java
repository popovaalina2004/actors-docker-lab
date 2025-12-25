package com.actors.entity;

import java.util.Objects;

public class Profile {
    private Long id;
    private String login;
    private String password;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Profile profile = (Profile) obj;
        if (!Objects.equals(id, profile.id)) return false;
        if (!Objects.equals(login, profile.login)) return false;
        return Objects.equals(id, profile.id) && Objects.equals(login, profile.login)
               && Objects.equals(password, profile.password);
    }

    public Profile(Long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
