package com.challenge.disneyApp.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class MoviesBasicDTO {
    private String image;
    private String title;
    private Date dateCreate;
}
