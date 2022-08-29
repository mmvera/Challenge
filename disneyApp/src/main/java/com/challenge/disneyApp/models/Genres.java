package com.challenge.disneyApp.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/*Clase Genero con sus atributos, getters y setters*/

@Entity
@Table(name="genres")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genres {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;
    private String name;
    private String urlImage;
    @OneToMany(mappedBy = "genre",fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Movies> movies;
}