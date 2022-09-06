package com.challenge.disneyApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharactersFiltersDTO {

    private String name;
    private Integer age;
    private Long idMovie;
}
