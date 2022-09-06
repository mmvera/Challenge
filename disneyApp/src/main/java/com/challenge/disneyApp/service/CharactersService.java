package com.challenge.disneyApp.service;
import com.challenge.disneyApp.DTO.CharactersBasicDTO;
import com.challenge.disneyApp.DTO.CharactersDTO;
import com.challenge.disneyApp.DTO.CharactersFiltersDTO;
import com.challenge.disneyApp.exception.ParamNotFound;
import com.challenge.disneyApp.mapper.CharactersMapper;
import com.challenge.disneyApp.models.Characters;
import com.challenge.disneyApp.models.CharactersMovies;
import com.challenge.disneyApp.repository.CharactersMoviesRepository;
import com.challenge.disneyApp.repository.CharactersRepository;
import com.challenge.disneyApp.repository.specifications.CharactersSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharactersService implements CharactersServiceI {
    @Autowired
    private CharactersMapper charactersMapper;
    @Autowired
    private CharactersRepository charactersRepository;
    @Autowired
    private CharactersMoviesRepository charactersMoviesRepository;
    @Autowired
    private MoviesService moviesService;
    @Autowired
    private CharactersSpecifications charactersSpecifications;

    public CharactersBasicDTO saveCharacters(CharactersDTO dto) {
        Characters character = charactersMapper.characterDtoTOcharacterFull(dto);
        Characters characterSaved = charactersRepository.save(character);
        CharactersBasicDTO charactersDTO = charactersMapper.characterTOcharacterDTO(characterSaved);
        List <Long> listMovies = dto.getListMovies();

        if(listMovies.size()!=0){
            //verifico si las peliculas existen
            if(!existMovieList(listMovies)){
                throw new ParamNotFound("the movie does not exist");
            }
            Long idCharacter = characterSaved.getCharacterId();
            // verifico si el registro characterMovie existe
            if(existRegCharacterMovie(idCharacter,listMovies)){
                throw new ParamNotFound("the register already exists");
            }
            // si pasa los dos filtros inserto los registros characterMovies
            listMovies.forEach((elem) -> {
                CharactersMovies charactersMovies = new CharactersMovies();
                charactersMovies.setCharacterId(idCharacter);
                charactersMovies.setMovieId(elem);
                charactersMoviesRepository.save(charactersMovies);
            });
            charactersDTO.setListMovies(listMovies);
        }
        return charactersDTO;
    }

    public void deleteById(Long id) {
        if (!idCharacterExist(id)) {
            throw new ParamNotFound("the character does not exist");
        }
        charactersRepository.deleteById(id);
    }

    public List<CharactersBasicDTO> findAll() {
        List<Characters> charactersList = charactersRepository.findAll();
        List<CharactersBasicDTO> charactersDtoList = charactersMapper.listCharactersTOlistCharactersDTO(charactersList);
        return charactersDtoList;
    }
    public CharactersBasicDTO findById(Long id){
        Characters characters = charactersRepository.findByCharacterId(id);
        CharactersBasicDTO charactersBasicDTO = charactersMapper.characterTOcharacterDTO(characters);
        return charactersBasicDTO;
    }
    public boolean idCharacterExist(Long id){
        return charactersRepository.existsByCharacterId(id);
    }
    public boolean existMovieList(List<Long> listMovies){
        boolean exist = false;
        for(int i=0;i< listMovies.size();i++){

            if(!moviesService.idMovieExist(listMovies.get(i))){
                return true;
            }
        }
        return exist;
    }
    public boolean existRegCharacterMovie(Long idCharacter, List<Long> listMovies){
        boolean exist = false;
        for (int i=0;i< listMovies.size();i++){
            if(charactersMoviesRepository.existsByMovieIdAndCharacterId(listMovies.get(i),idCharacter)){
                return true;
            }
        }
        return exist;
    }

    public CharactersDTO updateCharacter(CharactersDTO dto) {
        Long idCharacter = dto.getCharacterId();
        if(!idCharacterExist(idCharacter)){
            throw new ParamNotFound("the character does not exist");
        }
        Characters characters = charactersRepository.findByCharacterId(idCharacter);
        characters.setAge(dto.getAge());
        characters.setName(dto.getName());
        characters.setWeight(dto.getWeight());
        characters.setUrlImage(dto.getUrlImage());
        characters.setHistory(dto.getHistory());
        charactersRepository.save(characters);
        CharactersDTO charactersDTO = charactersMapper.characterTOcharacterDToFull(characters);
        return charactersDTO;
    }

    public List<CharactersBasicDTO> getByFilters(String name, Integer age, Long idMovie) {
        CharactersFiltersDTO filterCharacters = new CharactersFiltersDTO();
        filterCharacters.setAge(age);
        filterCharacters.setName(name);
        filterCharacters.setIdMovie(idMovie);
        List<Characters> characters = charactersRepository.findAll(charactersSpecifications.getFilters(filterCharacters));
        List<CharactersBasicDTO> listDTO = charactersMapper.listCharactersTOlistCharactersDTO(characters);
        return  listDTO;
    }

    public CharactersDTO findByCharacterId(Long id) {
        if((!idCharacterExist(id))){
            throw new ParamNotFound("the movie does not exist");
        }
        Characters character = charactersRepository.findByCharacterId(id);
        CharactersDTO charactersDTO = charactersMapper.characterTOcharacterDToFull(character);
        List<CharactersMovies> charactersMoviesList= charactersMoviesRepository.findByCharacterId(id);
        List<Long> listMovies = new ArrayList<>();
        charactersMoviesList.forEach((elem) -> {
            listMovies.add(elem.getMovieId());
        });
        charactersDTO.setListMovies(listMovies);
        return charactersDTO;
    }
}