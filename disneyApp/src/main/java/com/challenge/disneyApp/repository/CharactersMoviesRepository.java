package com.challenge.disneyApp.repository;

import com.challenge.disneyApp.models.CharactersMovies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CharactersMoviesRepository extends JpaRepository<CharactersMovies,Long> {
    List<CharactersMovies> findByMovieId(Long id);

    boolean existsByMovieIdAndCharacterId(Long idCharacter, Long idMovie);
}
