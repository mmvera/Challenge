package com.challenge.disneyApp.repository;

import com.challenge.disneyApp.models.Characters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface CharactersRepository extends JpaRepository<Characters,Long> {

    Characters findByName(String name);

    ArrayList<Characters> findByAge(Integer age);

    Characters findByCharacterId(Long id);

    boolean existsByCharacterId(Long id);
}
