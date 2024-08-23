package com.snowmiku.moviesearchapi.service;

import com.snowmiku.moviesearchapi.pojo.MovieCountDTO;

public interface MovieService {
    MovieCountDTO getMovieCountByYear(int year);
}