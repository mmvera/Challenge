package com.challenge.disneyApp.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CharactersBasicDTO {
    private Long id;
    private String name;
    private String image;
    private List<Long> listMovies;
}
