package com.actors.repository;

import com.actors.entity.Profile;
import com.actors.request.CreateProfileRequest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class ProfileRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ProfileRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Profile> findByLogin(String login) {
        return jdbcTemplate.query(
                "SELECT id, login, password FROM profile WHERE login = :login",
                Map.of("login", login),
                rs -> {
                    if (!rs.next()) {
                        return Optional.empty();
                    }
                    return Optional.of(new Profile(
                            rs.getLong("id"),
                            rs.getString("login"),
                            rs.getString("password"))
                    );
                }
        );
    }

    public void create(CreateProfileRequest createProfileRequest) {
        jdbcTemplate.update(
                "INSERT INTO profile(login, password) VALUES (:login, :password)",
                Map.of(
                        "login", createProfileRequest.getUsername(),
                        "password", createProfileRequest.getPassword()
                )
        );
    }

    public Optional<Profile> findById(Long id) {
        return jdbcTemplate.query(
                "SELECT id, login, password FROM profile WHERE id = :id",
                Map.of("id", id),
                rs -> {
                    if (!rs.next()) {
                        return Optional.empty();
                    }
                    return Optional.of(new Profile(
                            rs.getLong("id"),
                            rs.getString("login"),
                            rs.getString("password"))
                    );
                }
        );
    }
}
