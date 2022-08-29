package com.challenge.disneyApp.mapper;

import com.challenge.disneyApp.DTO.CharactersBasicDTO;
import com.challenge.disneyApp.DTO.CharactersDTO;
import com.challenge.disneyApp.models.Characters;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CharactersMapper {
    public Characters characterDtoTOcharacter(CharactersBasicDTO charactersDTO){
        Characters characters = new Characters();
        characters.setUrlImage(charactersDTO.getImage());
        characters.setName(charactersDTO.getName());
        return characters;
    }
    public CharactersBasicDTO characterTOcharacterDTO(Characters characters){
        CharactersBasicDTO charactersDTO = new CharactersBasicDTO();
        charactersDTO.setImage(characters.getUrlImage());
        charactersDTO.setName(characters.getName());
        return charactersDTO;
    }
    public List<CharactersBasicDTO> listCharactersTOlistCharactersDTO(List<Characters> charactersList){
        List<CharactersBasicDTO> listCharactersDTO = new ArrayList<>();
        charactersList.forEach((elem) -> {
            CharactersBasicDTO newCharacterDTO = characterTOcharacterDTO(elem);
            listCharactersDTO.add(newCharacterDTO);
        });
        return listCharactersDTO;
    }
    public Characters  characterDtoTOcharacterFull(CharactersDTO characterDTO){
        Characters characters = new Characters();
        characters.setName(characterDTO.getName());
        characters.setAge(characterDTO.getAge());
        characters.setUrlImage(characterDTO.getUrlImage());
        characters.setHistory(characterDTO.getHistory());
        characters.setWeight(characterDTO.getWeight());
        return characters;
    }
    public CharactersDTO  characterTOcharacterDToFull(Characters character){
        CharactersDTO charactersDTO = new CharactersDTO();
        charactersDTO.setName(character.getName());
        charactersDTO.setAge(character.getAge());
        charactersDTO.setUrlImage(character.getUrlImage());
        charactersDTO.setHistory(character.getHistory());
        charactersDTO.setWeight(character.getWeight());
        charactersDTO.setCharacterId(character.getCharacterId());
        return charactersDTO;
    }


}
