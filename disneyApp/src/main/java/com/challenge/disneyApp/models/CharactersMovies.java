package com.challenge.disneyApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "movies_characters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharactersMovies {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long movieId;
    private Long characterId;
}
