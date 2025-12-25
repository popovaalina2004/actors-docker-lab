package com.actors.repository;

import com.actors.entity.Actor;
import com.actors.request.CreateActorRequest;
import com.actors.request.UpdateActorRequest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ActorRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ActorRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void create(CreateActorRequest createActorRequest) {
        namedParameterJdbcTemplate.update(
                "INSERT INTO actor(name, birth_year, id_profile) VALUES (:name, :birth_year, :profileId)",
                Map.of(
                        "name", createActorRequest.name(),
                        "birth_year", createActorRequest.birth_year(),
                        "profileId", createActorRequest.profileId()
                ));
    }

    public void update(Long actorId, UpdateActorRequest request) {
        namedParameterJdbcTemplate.update(
                "UPDATE actor SET name = :name, birth_year = :birth_year WHERE id_actor = :id",
                Map.of(
                        "name", request.name(),
                        "birth_year", request.birth_year(),
                        "id", actorId
                )
        );
    }

    public void delete(Long id) {
        namedParameterJdbcTemplate.update(
                "DELETE FROM actor WHERE id_actor = :id",
                Map.of("id", id)
        );
    }

    public Optional<Actor> findById(Long actorId) {
        return namedParameterJdbcTemplate.query(
                "SELECT * FROM actor WHERE id_actor = :id",
                Map.of("id", actorId),
                (rs, rowNum) -> new Actor(
                        rs.getLong("id_actor"),
                        rs.getString("name"),
                        rs.getInt("birth_year"),
                        rs.getLong("id_profile")
                )
        ).stream().findFirst();
    }

    public List<Actor> findByName(String name) {
        return namedParameterJdbcTemplate.query(
                "SELECT * FROM actor WHERE name LIKE :name",
                Map.of("name", "%" + name + "%"),
                (rs, rowNum) -> new Actor(
                        rs.getLong("id_actor"),
                        rs.getString("name"),
                        rs.getInt("birth_year"),
                        rs.getLong("id_profile")
                )
        );
    }

    public List<Actor> findAll(String login) {
        return namedParameterJdbcTemplate.query(
                "SELECT ac.* FROM actor ac JOIN profile p ON ac.id_profile = p.id WHERE p.login = :login",
                Map.of("login", login),
                (rs, rowNum) -> new Actor(
                        rs.getLong("id_actor"),
                        rs.getString("name"),
                        rs.getInt("birth_year"),
                        rs.getLong("id_profile")
                )
        );
    }
}
