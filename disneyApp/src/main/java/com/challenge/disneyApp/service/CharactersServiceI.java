package com.challenge.disneyApp.service;

import com.challenge.disneyApp.DTO.CharactersBasicDTO;
import com.challenge.disneyApp.DTO.CharactersDTO;

import java.util.List;

public interface CharactersServiceI {
    CharactersBasicDTO findByName(String name);
    CharactersBasicDTO saveCharacters(CharactersDTO dto);
    List<CharactersBasicDTO> findByAge(Integer age);
    void deleteById(Long id);
    List<CharactersBasicDTO> findByMovie(Long idMovie);
    boolean idCharacterExist(Long id);
    boolean existMovieList(List<Long> listMovies);
    boolean existRegCharacterMovie(Long idCharacter, List<Long> listMovies);
    CharactersDTO updateCharacter(CharactersDTO charactersDTO);
}
