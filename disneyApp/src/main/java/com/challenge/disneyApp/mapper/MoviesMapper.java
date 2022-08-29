package com.challenge.disneyApp.mapper;

import com.challenge.disneyApp.DTO.MoviesBasicDTO;
import com.challenge.disneyApp.DTO.MoviesDTO;
import com.challenge.disneyApp.models.Movies;
import org.springframework.stereotype.Component;

@Component
public class MoviesMapper {
    public MoviesBasicDTO moviesTOmoviesDTO(Movies movie){
        MoviesBasicDTO moviesBasicDTO = new MoviesBasicDTO();
        moviesBasicDTO.setImage(movie.getUrlImage());
        moviesBasicDTO.setTitle(movie.getTitle());
        moviesBasicDTO.setDateCreate(movie.getDateCreate());
        return moviesBasicDTO;
    }
    public Movies moviesDtoTOmovies(MoviesBasicDTO movieDTO){
        Movies movies = new Movies();
        movies.setUrlImage(movieDTO.getImage());
        movies.setTitle(movieDTO.getTitle());
        movies.setDateCreate(movieDTO.getDateCreate());
        return movies;
    }
    public Movies moviesDtoTOmoviesFull(MoviesDTO movieDTO){
        Movies movie = new Movies();
        movie.setTitle(movieDTO.getTitle());
        movie.setUrlImage(movieDTO.getUrlImage());
        movie.setDateCreate(movieDTO.getDateCreate());
        movie.setRanking(movieDTO.getRanking());
        movie.setGenreId(movieDTO.getGenreId());
        return movie;
    }
}
