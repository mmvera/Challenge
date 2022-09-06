package com.challenge.disneyApp.repository;

import com.challenge.disneyApp.models.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends JpaRepository<Movies,Long> {
    Movies findByMovieId(Long id);
}
