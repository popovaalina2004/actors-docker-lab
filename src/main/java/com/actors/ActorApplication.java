package com.actors;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;

@SpringBootApplication
public class ActorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActorApplication.class, args);
    }
}
