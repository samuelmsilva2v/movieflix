package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Genre;
import lombok.Data;

@Data
public class GenreDTO {

    private Long id;
    private String name;

    public GenreDTO(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
    }

}
