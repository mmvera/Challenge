package com.challenge.disneyApp.repository;

import com.challenge.disneyApp.models.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoviesRepository extends JpaRepository<Movies,Long> {
    Optional<Movies> findByMovieId(Long id);
}
