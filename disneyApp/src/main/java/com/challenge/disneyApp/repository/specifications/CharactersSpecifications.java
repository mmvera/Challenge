package com.challenge.disneyApp.repository.specifications;

import com.challenge.disneyApp.DTO.CharactersFiltersDTO;
import com.challenge.disneyApp.models.Characters;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CharactersSpecifications {
    public Specification<Characters> getFilters (CharactersFiltersDTO charactersFiltersDTO){
        return null;
    }
}
