package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.services.GenreService;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@GetMapping("/{id}")
	public ResponseEntity<MovieDetailsDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(movieService.findById(id));
	}

	@GetMapping
	public ResponseEntity<Page<MovieCardDTO>> findByGenre(
			@RequestParam(value = "genreId", defaultValue = "0") Long genreId,
			Pageable pageable) {

		Pageable sortedPageable = PageRequest.of(
				pageable.getPageNumber(),
				pageable.getPageSize(),
				Sort.by("title")
		);

		return ResponseEntity.ok(movieService.findByGenre(genreId, sortedPageable));
	}
}
