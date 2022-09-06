package com.challenge.disneyApp.service;

import com.challenge.disneyApp.DTO.MoviesBasicDTO;
import com.challenge.disneyApp.DTO.MoviesDTO;
import com.challenge.disneyApp.exception.ParamNotFound;
import com.challenge.disneyApp.mapper.MoviesMapper;
import com.challenge.disneyApp.models.CharactersMovies;
import com.challenge.disneyApp.models.Movies;
import com.challenge.disneyApp.repository.CharactersMoviesRepository;
import com.challenge.disneyApp.repository.CharactersRepository;
import com.challenge.disneyApp.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MoviesService {
    @Autowired
    private MoviesMapper moviesMapper;
    @Autowired
    private MoviesRepository moviesRepository;
    @Autowired
    private CharactersService charactersService;
    @Autowired
    private CharactersMoviesRepository charactersMoviesRepository;
    public MoviesBasicDTO saveMovie(MoviesDTO dto) {
        Movies movie = moviesMapper.moviesDtoTOmoviesFull(dto);
        Movies movieSaved = moviesRepository.save(movie);
        MoviesBasicDTO movieDTO = moviesMapper.moviesTOmoviesDTO(movieSaved);
        List<Long> listCharacters = dto.getListCharacters();

        if(!existListCharacters(listCharacters)){
            throw new ParamNotFound("the character does not exist");
        }
        Long movieId = movieSaved.getMovieId();
        if(existRegMovieCharacterList(movieId,listCharacters)){
            throw new ParamNotFound("the register already exists");
        }
        listCharacters.forEach((elem) -> {
            CharactersMovies charactersMovies = new CharactersMovies();
            charactersMovies.setCharacterId(elem);
            charactersMovies.setMovieId(movieId);
            charactersMoviesRepository.save(charactersMovies);
        });
        movieDTO.setListCharacters(listCharacters);
        return  movieDTO;
    }

    public void deleteById(Long id) {
        moviesRepository.deleteById(id);
    }
    public boolean idMovieExist(Long id){
        return moviesRepository.existsById(id);
    }
    public boolean existListCharacters(List<Long> listCharacters) {
        boolean exist = false;
        for (int i = 0; i < listCharacters.size(); i++) {

            if (!charactersService.idCharacterExist(listCharacters.get(i))) {
                return true;
            }
        }
        return exist;
    }
    public boolean existRegMovieCharacterList(Long movieId, List<Long> listCharacters){
        boolean exist = false;
        for (int i=0;i< listCharacters.size();i++){
            if(charactersMoviesRepository.existsByMovieIdAndCharacterId(movieId,listCharacters.get(i))){
                return true;
            }
        }
        return exist;
    }

    public MoviesDTO findByMovieId(Long id) {
        if(!idMovieExist(id)){
            throw new ParamNotFound("the movie does not exist");
        }
        Movies movie = moviesRepository.findByMovieId(id);
        MoviesDTO moviesDTO = moviesMapper.moviesTOmoviesDtoFull(movie);
        List<CharactersMovies> charactersMoviesList= charactersMoviesRepository.findByMovieId(id);
        List<Long> listCharacters = new ArrayList<>();
        charactersMoviesList.forEach((elem) -> {
            listCharacters.add(elem.getCharacterId());
        });
        moviesDTO.setListCharacters(listCharacters);
        return moviesDTO;
    }

    public List<MoviesBasicDTO> findAll() {
     List<Movies> moviesList = moviesRepository.findAll();
     List<MoviesBasicDTO> listMoviesDTO = new ArrayList<>();
     moviesList.forEach((elem) -> {
         listMoviesDTO.add(moviesMapper.moviesTOmoviesDTO(elem));
     });
     return listMoviesDTO;
    }
}
