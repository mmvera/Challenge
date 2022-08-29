package com.challenge.disneyApp.service;

import com.challenge.disneyApp.DTO.MoviesBasicDTO;
import com.challenge.disneyApp.DTO.MoviesDTO;
import com.challenge.disneyApp.exception.ParamNotFound;
import com.challenge.disneyApp.mapper.MoviesMapper;
import com.challenge.disneyApp.models.Movies;
import com.challenge.disneyApp.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MoviesService {
    @Autowired
    private MoviesMapper moviesMapper;
    @Autowired
    private MoviesRepository moviesRepository;
    public MoviesBasicDTO saveMovie(MoviesDTO dto) {
        Movies movie = moviesMapper.moviesDtoTOmoviesFull(dto);
        Movies movieSaved = moviesRepository.save(movie);
        MoviesBasicDTO movieDTO = moviesMapper.moviesTOmoviesDTO(movieSaved);
        return  movieDTO;
    }
    public Optional<Movies> findById(Long id){

        if(!idMovieExist(id)){
            throw new ParamNotFound("the movie does not exist");
        }
        Optional<Movies> movie = moviesRepository.findByMovieId(id);
        return movie;
    }

    public void deleteById(Long id) {
        moviesRepository.deleteById(id);
    }
    public boolean idMovieExist(Long id){
        Optional<Movies> movie = moviesRepository.findByMovieId(id);
        if(!movie.isPresent()){
            return false;
        }
        return true;
    }
}
