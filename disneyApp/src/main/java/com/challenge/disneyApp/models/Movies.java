package com.challenge.disneyApp.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

/*Clase PeliculaSerie con sus atributos, getters y setters*/
@Entity
@Table(name = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movies {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    private String title;
    private String urlImage;
    private Date dateCreate;
    private Double ranking ;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="genre_id",insertable = false,updatable = false)
    private Genres genre;

    @Column(name = "genre_id")
    private Long genreId;
}
