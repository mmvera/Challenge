package com.challenge.disneyApp.DTO;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class MoviesDTO {
    private String title;
    private String urlImage;
    private Date dateCreate;
    private Double ranking;
    private Long genreId;
    private List<Long> ListCharacters;
}
