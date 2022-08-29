package com.challenge.disneyApp.service;

import com.challenge.disneyApp.exception.ParamNotFound;
import com.challenge.disneyApp.models.CharactersMovies;
import com.challenge.disneyApp.repository.CharactersMoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharactersMoviesService {
    @Autowired
    private CharactersMoviesRepository charactersMoviesRepository;
    public List<CharactersMovies> findByMovieId(Long id) {
        List<CharactersMovies> charactersMovies = charactersMoviesRepository.findByMovieId(id);
        if(charactersMovies.size()==0){
            throw new ParamNotFound("The movie does not exist");
        }
        return charactersMovies;
    }
    public CharactersMovies saveCharacterMovie(CharactersMovies characterMovie){
        Long idMovie = characterMovie.getMovieId();
        Long idCharacter = characterMovie.getCharacterId();
        CharactersService charactersService = new CharactersService();
        MoviesService moviesService = new MoviesService();
        boolean existMovie = moviesService.idMovieExist(idMovie);
        if(!existMovie){
            throw new ParamNotFound("the movie does not exist");
        }
        boolean existCharacter = charactersService.idCharacterExist(idCharacter);
        if(!existCharacter){
            throw new ParamNotFound("the character does not exist");
        }
        boolean existRegister = existRegister(idCharacter,idMovie);
        if(!existRegister){
            throw new ParamNotFound("the register already exists");
        }
        CharactersMovies charactersMovie =  charactersMoviesRepository.save(characterMovie);
        return charactersMovie;
    }

    public boolean existRegister(Long idCharacter, Long idMovie){
        return charactersMoviesRepository.existsByMovieIdAndCharacterId(idCharacter,idMovie);
    }

}
