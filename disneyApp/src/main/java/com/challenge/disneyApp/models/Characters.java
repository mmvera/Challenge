package com.challenge.disneyApp.models;

import lombok.*;
import javax.persistence.*;


/*Clase Personaje con sus atributos, getters y setters*/

@Entity
@Table(name="characters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Characters {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long characterId;
    private String name;
    private String urlImage;
    private Integer age;
    private Double weight;
    private String history;
}
