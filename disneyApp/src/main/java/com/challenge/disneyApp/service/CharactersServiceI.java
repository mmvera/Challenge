package com.challenge.disneyApp.service;

import com.challenge.disneyApp.DTO.CharactersBasicDTO;
import com.challenge.disneyApp.DTO.CharactersDTO;

import java.util.List;

public interface CharactersServiceI {
    CharactersBasicDTO saveCharacters(CharactersDTO dto);
    void deleteById(Long id);
    boolean idCharacterExist(Long id);
    boolean existMovieList(List<Long> listMovies);
    boolean existRegCharacterMovie(Long idCharacter, List<Long> listMovies);
    CharactersDTO updateCharacter(CharactersDTO charactersDTO);

    List<CharactersBasicDTO> getByFilters(String name, Integer age, Long idMovie);
    CharactersDTO findByCharacterId(Long characterId);
}
