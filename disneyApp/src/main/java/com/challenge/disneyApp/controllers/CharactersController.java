package com.challenge.disneyApp.controllers;

import com.challenge.disneyApp.DTO.CharactersBasicDTO;
import com.challenge.disneyApp.DTO.CharactersDTO;
import com.challenge.disneyApp.service.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharactersController {
    @Autowired
    private CharactersService charactersService;
    @GetMapping()
    public ResponseEntity<List<CharactersBasicDTO>> getAllCharacters(){
        List<CharactersBasicDTO> allCharacters= charactersService.findAll();
        return ResponseEntity.ok().body(allCharacters);
    }

    @PostMapping
    public ResponseEntity<CharactersBasicDTO> createdCharacters(@RequestBody CharactersDTO characters){
        CharactersBasicDTO charactersSaved = charactersService.saveCharacters(characters);
        return ResponseEntity.status(HttpStatus.CREATED).body(charactersSaved);
    }
    @DeleteMapping(path = "{charactersId}")
    public ResponseEntity<Void> deleteById(@PathVariable("charactersId") Long id){
        this.charactersService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/name")
    public ResponseEntity<List<CharactersBasicDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) Long idMovie
    ){
        List<CharactersBasicDTO> listCharacters = charactersService.getByFilters(name,age,idMovie);
        return ResponseEntity.ok(listCharacters);
    }
    @PutMapping
    public ResponseEntity<CharactersDTO> updateCharacter(@RequestBody CharactersDTO dto){
        CharactersDTO charactersDTO = charactersService.updateCharacter(dto);
        return ResponseEntity.ok().body(charactersDTO);
    }
    @GetMapping("/{characterId}")
    public ResponseEntity<CharactersDTO> characterDetails(@PathVariable Long characterId){
        CharactersDTO charactersDTO = charactersService.findByCharacterId(characterId);
        return ResponseEntity.ok().body(charactersDTO);
    }
}
