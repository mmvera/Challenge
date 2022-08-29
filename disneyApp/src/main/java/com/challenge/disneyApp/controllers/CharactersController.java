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
    @GetMapping("")
    public ResponseEntity<List<CharactersBasicDTO>> getAllCharacters(){
        List<CharactersBasicDTO> allCharacters= charactersService.findAll();
        return ResponseEntity.ok().body(allCharacters);
    }

    @GetMapping(params = "name")
    public ResponseEntity<CharactersBasicDTO> findByName(@RequestParam("name") String name){

        CharactersBasicDTO charactersResponse = charactersService.findByName(name);
        return ResponseEntity.ok().body(charactersResponse);
    }
    @PostMapping
    public ResponseEntity<CharactersBasicDTO> createdCharacters(@RequestBody CharactersDTO characters){
        CharactersBasicDTO charactersSaved = charactersService.saveCharacters(characters);
        return ResponseEntity.status(HttpStatus.CREATED).body(charactersSaved);
    }
    @GetMapping(params = "age")
    public ResponseEntity<List<CharactersBasicDTO>> findByAge(@RequestParam("age") Integer age){

        List<CharactersBasicDTO> charactersDTO =  charactersService.findByAge(age);
        return ResponseEntity.ok().body(charactersDTO);
    }

    @DeleteMapping(path = "{charactersId}")
    public ResponseEntity<Void> deleteById(@PathVariable("charactersId") Long id){
        this.charactersService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping(params = "idMovie")
    public ResponseEntity<List<CharactersBasicDTO>> findByMovie(@RequestParam("age") Long idMovie){
        List<CharactersBasicDTO> charactersDTO =  charactersService.findByMovie(idMovie);
        return ResponseEntity.ok().body(charactersDTO);
    }
    @PutMapping
    public ResponseEntity<CharactersDTO> updateCharacter(@RequestBody CharactersDTO dto){
        CharactersDTO charactersDTO = charactersService.updateCharacter(dto);
        return ResponseEntity.ok().body(charactersDTO);
    }
}
