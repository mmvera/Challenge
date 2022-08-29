package com.challenge.disneyApp.controllers;

import com.challenge.disneyApp.DTO.MoviesBasicDTO;
import com.challenge.disneyApp.DTO.MoviesDTO;
import com.challenge.disneyApp.models.Movies;
import com.challenge.disneyApp.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MoviesController {
    @Autowired
    private MoviesService moviesService;
    @PostMapping
    public ResponseEntity<MoviesBasicDTO> createdMovie(@RequestBody MoviesDTO movieDTO){
        MoviesBasicDTO movieSaved = moviesService.saveMovie(movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);
    }
    @DeleteMapping(path = "{movieId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable("movieId") Long id){
        moviesService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
