package com.actors.entity;

public class Actor {
    private Long id_actor;
    private String name;
    private int birth_year;
    private Long idProfile;

    public Actor(Long id_actor, String name, int birth_year, Long idProfile) {
        this.id_actor = id_actor;
        this.name = name;
        this.birth_year = birth_year;
        this.idProfile = idProfile;
    }

    public Long getId_actor() { return id_actor; }

    public void setId_actor(Long id_actor) { this.id_actor = id_actor; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getBirth_year() { return birth_year; }

    public void setBirth_year(int birth_year) { this.birth_year = birth_year; }

    public Long getIdProfile() { return this.idProfile; }

    public void setIdProfile(Long idProfile) { this.idProfile = idProfile; }
}
