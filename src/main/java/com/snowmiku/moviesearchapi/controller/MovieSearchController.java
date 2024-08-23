package com.snowmiku.moviesearchapi.controller;

import com.snowmiku.moviesearchapi.pojo.MovieCountDTO;
import com.snowmiku.moviesearchapi.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MovieSearchController {
    private final MovieService movieService;

    @Autowired
    private MovieSearchController(@Qualifier("movieServiceImpl") MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseEntity<MovieCountDTO> getMoviesByYear(@RequestParam("year") int year) {
        MovieCountDTO movieCount = movieService.getMovieCountByYear(year);
        return ResponseEntity.ok(movieCount);
    }
}