package com.challenge.disneyApp.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CharactersDTO {
    private Long characterId;
    private String name;
    private String urlImage;
    private Integer age;
    private Double weight;
    private String history;
    private List<Long> ListMovies;
}
